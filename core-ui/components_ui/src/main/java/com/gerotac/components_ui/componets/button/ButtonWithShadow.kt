package com.gerotac.components_ui.componets.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gerotac.components_ui.componets.text.TextBasic

@Composable
fun ButtonWithShadow(
    modifier: Modifier = Modifier,
    color: Color,
    shape: Shape,
    onClick: () -> Unit,
    textoBoton: String
) {
    Button(
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults.buttonColors(color),
        onClick = { onClick() }
    ) {
        Text(
            modifier = Modifier
                .padding(all = 4.dp),
            textAlign = TextAlign.Center,
            text = textoBoton,
            fontSize = 15.sp,
            color = Color.White
        )
    }
}