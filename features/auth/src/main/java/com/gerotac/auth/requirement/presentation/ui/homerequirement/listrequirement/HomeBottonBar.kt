package com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeBottomBar(
    showPrevious: Boolean,
    showNext: Boolean,
    onPreviousPressed: () -> Unit,
    onNextPressed: () -> Unit
) {
    BottomAppBar(
        modifier = Modifier
            .background(Color.White)
            .shadow(elevation = 10.dp, RoundedCornerShape(30.dp)),
        backgroundColor = Color(0xFFE2E1E1),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 10.dp,
            end = 20.dp,
            bottom = 20.dp
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp)
                .offset(y = (-0).dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFE2E1E1))
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                content = {
                    OutlinedButton(
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(10.dp)
                            .offset(y = (5).dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF21130C)
                        ),
                        enabled = showPrevious,
                        onClick = onPreviousPressed,
                        border = BorderStroke(
                            width = 2.dp,
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color(0xFFFFFFFF),
                                    Color(0xFFFFFFFF)
                                )
                            )
                        )
                    ) {
                        Icon(
                            Icons.Filled.SkipPrevious,
                            contentDescription = "Anterior",
                            tint = Color.White,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    OutlinedButton(
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(10.dp)
                            .offset(y = (5).dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF21130C)
                        ),
                        enabled = showNext,
                        onClick = onNextPressed,
                        border = BorderStroke(
                            width = 2.dp,
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color(0xFFFFFFFF),
                                    Color(0xFFFFFFFF)
                                )
                            )
                        )
                    ) {
                        Icon(
                            Icons.Filled.SkipNext,
                            contentDescription = "Siguiente",
                            tint = Color.White,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                    }
                })
        }
    }
}

