package com.example.readercomposeapp.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.readercomposeapp.components.LoginButton
import com.example.readercomposeapp.components.LoginForm
import com.example.readercomposeapp.components.SignUpElement

@Composable
fun LoginScreen(navController: NavController) {
    Surface(modifier = Modifier.padding(24.dp)) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Reader", modifier = Modifier.padding(18.dp), fontSize = 68.sp, fontWeight = FontWeight.W400,  color = Color.Blue)
            LoginForm()
            LoginButton(modifier = Modifier)
            Spacer(modifier = Modifier.padding(16.dp))
            SignUpElement()
        }
    }
}