package com.example.readercomposeapp.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.readercomposeapp.R
import com.example.readercomposeapp.components.BookSearcherTopAppBar
import com.example.readercomposeapp.navigation.ReaderScreens

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            BookSearcherTopAppBar(
                modifier = Modifier,
                backgroundColor = Color.White,
                elevation = 0.dp,
                iconColor = colorResource(R.color.light_blue),
                title = "Book Searcher",
                signout = {navController.navigate(ReaderScreens.LoginScreen.name)}
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text("Hello Scaffold", fontSize = 28.sp)
        }
    }
}