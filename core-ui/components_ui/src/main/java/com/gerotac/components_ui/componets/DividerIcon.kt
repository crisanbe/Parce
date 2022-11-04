package com.gerotac.components_ui.componets

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DividerIcon() {
    Divider(
        modifier = Modifier
            .width(34.3.dp)
            .height(30.dp)
            .padding(start = 33.dp)
    )
}
