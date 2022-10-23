package com.parce.components_ui.componets

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TextButtonNavigation(
    fontSize: TextUnit = 16.sp,
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = { onClick.invoke() }
    ) {
        Text(
            text = text,
            color = Color(0xFF33231B),
            fontSize = fontSize,
            textAlign = TextAlign.Center
        )
    }
}
