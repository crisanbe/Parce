package com.gerotac.components_ui.componets.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun TextBasic(
    text: String,
    textColor: Color,
    textAlign: TextAlign,
    fontFamily: FontFamily,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        color = textColor,
        textAlign = textAlign,
        fontFamily = fontFamily,
        fontSize = fontSize,
        fontWeight = fontWeight,
        maxLines = maxLines,
        modifier = modifier,
        overflow = overflow
    )

}