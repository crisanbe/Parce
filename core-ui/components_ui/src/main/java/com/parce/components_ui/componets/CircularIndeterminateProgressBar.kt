@file:Suppress("UNUSED_EXPRESSION")

package com.parce.components_ui.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean) {
    if (isDisplayed) {
        Dialog(
            onDismissRequest = { isDisplayed },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(500.dp)
                    .background(Color.Transparent, shape = RoundedCornerShape(9.dp))
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .height(25.dp),
                    color = Color.Yellow
                )
                Text(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    text = "Cargando..."
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressPreview() {
    CircularIndeterminateProgressBar(isDisplayed = true)
}
