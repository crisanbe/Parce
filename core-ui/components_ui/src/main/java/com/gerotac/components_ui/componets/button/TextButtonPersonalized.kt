package com.gerotac.components_ui.componets.button

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TextButtonPersonalized(
    color: Color,
    modifier: Modifier = Modifier,
    onclick: () -> Unit,
    text: String,
    fontText: FontWeight,
    styleText: TextStyle,
    fontSize: TextUnit

) {
    TextButton(
        modifier = modifier,
        colors = ButtonDefaults.textButtonColors(backgroundColor = color),
        onClick = { onclick() }
    ) {
        Text(
            text = text,
            fontWeight = fontText,
            style = styleText,
            fontSize = fontSize
        )
    }
}