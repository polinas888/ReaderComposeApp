import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SplashScreen(navController: NavController) {
    Pulsating {
        Surface(
        modifier = Modifier
            .padding(1.dp)
            .size(330.dp),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(4.dp, Color.LightGray)
    ) {
            Column(
                modifier = Modifier.padding(1.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "A.Reader", style = MaterialTheme.typography.h3, color = Color.Blue)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "Read. improve. Yourself",
                    style = MaterialTheme.typography.h5,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun Pulsating(pulseFraction: Float = 1.2f, durationMillis: Int = 500, content: @Composable () -> Unit) {
    val scaleAnimation = remember {
        Animatable(1f) // automatically change value in animateTo
    }

    LaunchedEffect(Unit) {
        val scaleUpAnimation = tween<Float>(durationMillis)
        val scaleDownAnimation = tween<Float>(durationMillis)

            scaleAnimation.animateTo(pulseFraction, scaleUpAnimation)
            scaleAnimation.animateTo(1f, scaleDownAnimation)
    }

    val scale = scaleAnimation.value

    Box(modifier = Modifier.scale(scale)) {
        content()
    }
}