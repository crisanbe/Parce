package com.gerotac.components_ui.componets.button

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun IconButtonDelete(onclick:()-> Unit,modifier: Modifier = Modifier) {
    var color by remember { mutableStateOf(Color(0xFFFDD835)) }

    Card(
        modifier = modifier
            .size(35.dp)
            .clip(CircleShape)                       // clip to the circle shape
            .border(2.dp, Color.Gray, CircleShape),
                contentColor = Color.Black,
        elevation = 10.dp,
        backgroundColor= Color.Black
    ) {
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
}