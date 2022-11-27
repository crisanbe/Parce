package com.gerotac.components_ui.componets.button

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonValidation(
    text: String,
    fontSize: TextUnit = 20.sp,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick.invoke() },
        shape = RoundedCornerShape(percent = 45),
        modifier = Modifier.size(height = 50.dp, width = 300.dp),
        colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Black)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = fontSize
        )
    }
}

@Composable
fun ButtonValidationLogin(
    isLoginEnable: Boolean,
    text: String,
    onClickAction: () -> Unit,
    fontSize: TextUnit = 20.sp
) {
    Button(
        onClick = { onClickAction() },
        enabled = isLoginEnable,
        shape = RoundedCornerShape(percent = 45),
        modifier = Modifier.size(height = 50.dp, width = 300.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF030303),
            disabledBackgroundColor = Color(0xFFFFD164),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = fontSize
        )
    }
}
