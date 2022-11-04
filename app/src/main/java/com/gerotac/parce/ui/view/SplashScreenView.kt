package com.gerotac.parce.ui.view

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.gerotac.parce.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreenView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = MaterialTheme.colors.isLight
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
        }
        ImageSplash()
        LoadingAnimationSplash()
    }
}

@Composable
fun ImageSplash() = Image(
    painter = painterResource(id = R.drawable.splash_parce), contentDescription = null
)

@Composable
fun LoadingAnimationSplash(
    circleColor: Color = Color.Yellow, animationDelay: Int = 1500
) {
    val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) })
    circles.forEachIndexed { index, animation ->
        LaunchedEffect(Unit) {
            delay(timeMillis = (animationDelay / 3L) * (index + 1))
            animation.animateTo(
                targetValue = 1f, animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDelay, easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }
    Box(
        modifier = Modifier
            .size(size = 200.dp)
            .background(color = Color.Transparent)
    ) {
        circles.forEachIndexed { _, animation ->
            Box(
                modifier = Modifier
                    .scale(scale = animation.value)
                    .size(size = 200.dp)
                    .clip(shape = CircleShape)
                    .background(color = circleColor.copy(alpha = (1 - animation.value)))
            )
        }
    }
}
