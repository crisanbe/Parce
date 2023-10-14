package com.gerotac.parce.ui.animations

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.gerotac.parce.ui.theme.ShimmerColorShades

@Composable
fun ShimmerAnimation() {
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(
                easing = FastOutSlowInEasing,
                delayMillis = 5000
                ),
            repeatMode = RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    /*HomeCompany(
        title = DrawerScreens.CompanyHome,
        navController = rememberNavController(),
        scaffoldState = rememberScaffoldState(),
        nameUser = null,
        brush = brush,
        onClickIconButton = {},
        onClickDestination = {}
    )*/
}