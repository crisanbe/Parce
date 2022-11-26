package com.gerotac.components_ui.componets.button

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.gerotac.components_ui.componets.text.TextBasic

@Composable
fun BotonConTexto(
    modifier: Modifier = Modifier,
    color: Color,
    onClick: () -> Unit,
    textoBoton: String,
    colorTexto: Color,
    alinearTexto: TextAlign,
    tamañoFuente: TextUnit,
    pesoFuente: FontWeight
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(color),
        onClick = { onClick() }
    ) {
        TextBasic(
            text = textoBoton,
            textColor = colorTexto,
            textAlign = alinearTexto,
            fontFamily = FontFamily.Monospace,
            fontSize = tamañoFuente,
            fontWeight = pesoFuente
        )
    }
}