package com.example.readercomposeapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.readercomposeapp.R

@Composable
fun BookCard(
    imageUrl: String,
    title: String,
    author: String,
    reading: String,
) {
    Card(
        modifier = Modifier
            .width(210.dp)
            .padding(20.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp
    ) {
        Column() {
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = imageUrl
                    ),
                    contentDescription = "book image",
                    modifier = Modifier
                        .size(100.dp),
                    contentScale = ContentScale.Crop
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(R.drawable.favorite),
                        contentDescription = "Heart Icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Rating()
                }
            }
            BookNameAndAuthor()
                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(R.color.light_blue),
                            shape = RoundedCornerShape(topStart = 24.dp)
                        )
                        .padding(8.dp)
                        .align(End)
                ) {
                    Text(
                        text = "Reading",
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }
            }
        }
    }

@Composable
private fun Rating() {
    Surface(
        modifier = Modifier.padding(top = 10.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Star Icon",
                tint = Color.Black
            )
            Text(text = "0.0")
        }
    }
}

@Composable
private fun BookNameAndAuthor() {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "Flutter in Action",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            text = "Author",
            style = MaterialTheme.typography.subtitle2
        )
    }
}

