package com.example.readercomposeapp.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.readercomposeapp.R
import com.example.readercomposeapp.components.BookCard
import com.example.readercomposeapp.components.BookSearcherTopAppBar
import com.example.readercomposeapp.components.FloatingAddButton
import com.example.readercomposeapp.navigation.BookSearcherScreens
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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
                signout = { navController.navigate(BookSearcherScreens.LoginScreen.name) }
            )
        },
        floatingActionButton = { FloatingAddButton() },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = true // Ensure the FAB is always visible
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp, 0.dp)
                    .fillMaxWidth(),
                Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Your reading\nactivity right now...", fontSize = 20.sp)
                UserImageAndName(navController)
            }
            BookCard( imageUrl = "http://books.google.com/books/content?id=M7ngCAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                title = "Flutter in Action",
                author = "Author",
                reading = "Reading")
            Text(text = "Reading list", modifier = Modifier.padding(start = 20.dp), fontSize = 20.sp)
        }
    }
}

@Composable
private fun UserImageAndName(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        UserImage(navController)
        Text(
            text = Firebase.auth.currentUser?.email?.split("@")?.get(0) ?: "No name",
            modifier = Modifier.padding(4.dp),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.blue)
        )
    }
}

@Composable
private fun UserImage(navController: NavController) {
    Box(
        modifier = Modifier
            .background(
                colorResource(R.color.light_blue),
                shape = CircleShape
            )
            .size(35.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.avatar_icon),
            contentDescription = "avatar picture",
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.Center)
                .clickable {
                    navController.navigate(BookSearcherScreens.StatsScreen.name)
                },
            colorFilter = ColorFilter.tint(
                color = Color.White
            )
        )
    }
}