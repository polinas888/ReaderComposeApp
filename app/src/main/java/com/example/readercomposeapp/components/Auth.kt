package com.example.readercomposeapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.readercomposeapp.R


@Composable
fun LoginForm() {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    val valid = remember(email, password) {
        email.trim().isNotEmpty() && password.trim().isNotEmpty()
    }
    var isLogin by rememberSaveable { mutableStateOf(true) }

    Text(
        text = if (!isLogin) LocalContext.current.getString(R.string.valid_credential_text) else "",
        fontSize = 18.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    InputField(
        title = "Email",
        type = KeyboardType.Email,
        input = email,
        onInputChanged = { email = it })
    InputField(
        title = "Password",
        type = KeyboardType.Password,
        input = password,
        isPassword = true,
        passwordVisibility = passwordVisibility,
        onInputChanged = { password = it },
        onPasswordVisibilityChanged = { passwordVisibility = !passwordVisibility })
    SubmitButton(modifier = Modifier, valid, isLogin)
    Spacer(modifier = Modifier.padding(16.dp))
    SignUpOrLoginChooser(isLogin) { isLogin = !isLogin }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputField(
    title: String,
    type: KeyboardType,
    input: String,
    isPassword: Boolean = false,
    passwordVisibility: Boolean = false,
    onInputChanged: (String) -> Unit,
    onPasswordVisibilityChanged: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = input,
        singleLine = true,
        visualTransformation = if (isPassword && !passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
        onValueChange = {
            onInputChanged(it)
        },
        placeholder = { Text(text = title) },
        label = { Text(text = title) },
        keyboardOptions = KeyboardOptions(
            keyboardType = type, imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }),
        trailingIcon = {
            if (isPassword) {
                PasswordVisibilityElement(passwordVisibility, onPasswordVisibilityChanged)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
private fun PasswordVisibilityElement(
    passwordVisibility: Boolean,
    onPasswordVisibilityChanged: () -> Unit
) {
    val image = if (passwordVisibility)
        Icons.Filled.Visibility
    else Icons.Filled.VisibilityOff

    val description = if (!passwordVisibility) "Hide password" else "Show password"

    IconButton(onClick = onPasswordVisibilityChanged) {
        Icon(imageVector = image, description)
    }
}

@Composable
fun SubmitButton(modifier: Modifier, valid: Boolean, isLogin: Boolean) {
    OutlinedButton(
        onClick = { },
        modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.LightGray),
        enabled = valid
    ) {
        Text(text = if (isLogin) "Login" else "Create account")
    }
}

@Composable
fun SignUpOrLoginChooser(isLogin: Boolean, onAuthOptionClick: () -> Unit) {
    Row {
        Text(text = "New user?", modifier = Modifier.padding(end = 6.dp), fontSize = 16.sp)
        Text(
            modifier = Modifier.clickable { onAuthOptionClick() },
            text = if (isLogin) "Sign up" else "Login",
            color = Color.Blue,
            fontSize = 18.sp
        )
    }
}
