package com.example.readercomposeapp.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.readercomposeapp.R

@Composable
fun FloatingAddButton() {
    FloatingActionButton(
        onClick = {
            //OnClick Method
        },
        backgroundColor = colorResource(R.color.light_blue),
        shape = RoundedCornerShape(50.dp),
    ) {
        Icon(
            imageVector = Icons.Rounded.Add,
            contentDescription = "Add FAB",
            tint = Color.White,
        )
    }
}