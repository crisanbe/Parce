package com.gerotac.auth.profileUser.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun FloatingButton(text: String, onClickFloatingButton: () -> Unit) {
    var extendedState by remember { mutableStateOf(value = true) }
    var secondsDisappear by remember { mutableStateOf(value = 2) }
    FloatingActionButton(
        modifier = Modifier.offset(y = 42.dp),
        onClick = { onClickFloatingButton.invoke() },
        backgroundColor = Color(0xFF030202)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = null,
                tint = Color.White
            )
            //AnimatedVisibility(extendedState) {
                Text(
                    text = text,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 3.dp)
                )
            //}
            LaunchedEffect(extendedState) {
                while (secondsDisappear > 0) {
                    delay(1.seconds)
                    secondsDisappear -= 1
                }
                extendedState = false
            }
        }
    }
}