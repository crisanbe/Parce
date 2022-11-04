package com.gerotac.components_ui.componets.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gerotac.components_ui.componets.MySpace

@Composable
fun CardView(
    item: String,
    elevation: Dp,
    text : String,
    border: BorderStroke? = null
) {

        Card(
        elevation = elevation,
        border = border,
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            Modifier
                .background(color = Color.White)
                .padding(5.dp)
                .width(300.dp)
                .height(35.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
            Text(
                text = item,
                color = Color.Black,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }

    MySpace(size = 5)
}