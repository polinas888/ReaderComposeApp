package com.example.readercomposeapp.screens.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.readercomposeapp.R
import com.example.readercomposeapp.navigation.ReaderScreens

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Reader",
            modifier = Modifier.padding(22.dp),
            fontSize = 68.sp,
            fontWeight = FontWeight.W400,
            color = Color.Blue
        )
        LoginForm(navController)
    }
}

@Composable
fun LoginForm(navController: NavController) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    val valid = remember(email, password) {
        email.trim().isNotEmpty() && password.trim().isNotEmpty()
    }
    var isLogin by rememberSaveable { mutableStateOf(true) }
    val isKeyboardOpen = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(if (isKeyboardOpen.value) 200.dp else 1000.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (!isLogin) LocalContext.current.getString(R.string.valid_credential_text) else "",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        InputField(
            title = "Email",
            type = KeyboardType.Email,
            input = email,
            onInputChanged = { email = it },
            onFocusChanged = { isKeyboardOpen.value = it })
        InputField(
            title = "Password",
            type = KeyboardType.Password,
            input = password,
            isPassword = true,
            passwordVisibility = passwordVisibility,
            onInputChanged = { password = it },
            onPasswordVisibilityChanged = { passwordVisibility = !passwordVisibility },
            onFocusChanged = { isKeyboardOpen.value = it })
        SubmitButton(modifier = Modifier, valid, isLogin, email, password, navController)
        Spacer(modifier = Modifier.padding(16.dp))
        SignUpOrLoginChooser(isLogin) { isLogin = !isLogin }
    }
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
    onPasswordVisibilityChanged: () -> Unit = {},
    onFocusChanged: (Boolean) -> Unit = {}
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
                onFocusChanged(false)
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
            .onFocusChanged { onFocusChanged(it.isFocused) }
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
fun SubmitButton(
    modifier: Modifier,
    valid: Boolean,
    isLogin: Boolean,
    email: String,
    password: String,
    navController: NavController,
    loginViewModel: ReaderLoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    OutlinedButton(
        onClick = {
            if (isLogin) {
                loginViewModel.loginWithEmailAndPassword(email, password) { success ->
                    if (success) {
                        navController.navigate(ReaderScreens.HomeScreen.name)
                    } else {
                        Toast.makeText(context, "Wrong email or password", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                loginViewModel.createUserWithEmailAndPassword(email, password) { success ->
                    if (success) {
                        navController.navigate(ReaderScreens.HomeScreen.name)
                    } else {
                        Toast.makeText(
                            context,
                            "Can't create account with that e-mail or password",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        },
        modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.LightGray),
        enabled = valid
    ) {
        if (loginViewModel.loading.value == true) {
            Text(text = "Creating account...")
            CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
        } else {
            Text(text = if (isLogin) "Login" else "Create account")
        }
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