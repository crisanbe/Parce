package com.gerotac.components_ui.componets.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.gerotac.components_ui.componets.text.TextBasic

@Composable
fun ButtonWithIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit,
    textButton: String
) {
    OutlinedButton(border = BorderStroke(3.dp,Color(0xFFFDD835)), onClick = { onClick() }) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(textButton, fontWeight = FontWeight.Bold)
    }
}