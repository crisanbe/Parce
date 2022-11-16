package com.gerotac.components_ui.componets.progress

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay

@Composable
fun DeterminedCircularProgress(progress: Float,isDisplayed: Boolean) {
    val percentage: Int = (progress * 100).toInt()

    if (isDisplayed) {
    Box(contentAlignment = Alignment.Center) {
        Text(text = "$percentage%", style = MaterialTheme.typography.body2)
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier.size(70.dp)
        )
    }
    }
}

@Composable
fun UploadFileView(isDisplayed: Boolean) {
    var progress by remember { mutableStateOf(0.0f) }
    LaunchedEffect(true) {
        for (i in 0..100 step 10) {
            delay(300)
            if (i == 100) {
                cancel()
            }
            progress = i / 100f
        }
    }
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )
    Column(
        Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .border(width = 1.dp, color = Color.Transparent, shape = RoundedCornerShape(2.dp)),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        DeterminedCircularProgress(progress = animatedProgress,isDisplayed)
    }

}