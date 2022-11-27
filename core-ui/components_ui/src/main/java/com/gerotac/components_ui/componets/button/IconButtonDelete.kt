package com.gerotac.components_ui.componets.button

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun IconButtonDelete(onclick:()-> Unit,modifier: Modifier = Modifier) {
    var color by remember { mutableStateOf(Color(0xFFCA3D3D)) }

    IconButton(
        onClick = {
            onclick()
            val randomColor = Color(0xFF861E1E)
            color = randomColor
        }) {
        Icon(
            Icons.Filled.Delete,
            contentDescription = "Cambiar color",
            tint = color
        )
    }
}