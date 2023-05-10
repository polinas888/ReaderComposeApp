package com.example.readercomposeapp.components

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.readercomposeapp.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun BookSearcherTopAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    elevation: Dp,
    iconColor: Color,
    title: String,
    signout: () -> Unit = {}
) {
    TopAppBar(modifier.padding(12.dp), backgroundColor = backgroundColor, elevation = elevation) {
        IconButton(onClick = { }) {
            Icon(
                Icons.Filled.Book,
                contentDescription = "Book image",
                tint = iconColor
            )
        }
        Text(
            title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.blue)
        )
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { onSignOut(signout) }) {
            Icon(
                Icons.Filled.Logout,
                contentDescription = "Logout image",
                tint = iconColor
            )
        }
    }
}

fun onSignOut(signOut: () -> Unit) {
    val auth = Firebase.auth
    try {
        auth.signOut()
        signOut()
    } catch (ex: Exception) {
        signOut()
        Log.d("FB", "Sign Out Error: ${ex.message}")
    }
}