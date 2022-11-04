package com.gerotac.components_ui.componets.button

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GradientBorderButtonRound(
    colors: List<Color>,
    context: Context,
    paddingValues: PaddingValues,
    widthFraction: Float
) {
    val paddingModifier = Modifier.padding(10.dp)
    val colors = listOf(Color(0xFF14140C), Color(0xFFFFC107))
    val context = LocalContext.current.applicationContext
    val paddingValues = PaddingValues(horizontal = 24.dp, vertical = 1.dp)
    val widthFraction = 0.68f
    Box(
        modifier = Modifier
            .fillMaxWidth(fraction = widthFraction)
            .border(
                width = 4.dp,
                brush = Brush.horizontalGradient(colors = colors),
                shape = RoundedCornerShape(percent = 20)
            )
            // To make the ripple round
            .clip(shape = RoundedCornerShape(percent = 50))
            .clickable {

            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Actualizar",
            fontSize = 26.sp,
            modifier = Modifier.padding(paddingValues),
            fontWeight = FontWeight.Medium
        )
    }
}