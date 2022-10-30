package com.parce.auth.requirement.presentation.ui.homerequirement

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.parce.components_ui.R

@Composable
fun HomeBottomBar(
    showPrevious: Boolean,
    showNext: Boolean,
    onPreviousPressed: () -> Unit,
    onNextPressed: () -> Unit
) {
    BottomAppBar(
        backgroundColor = Color(0xFFC7C7C7),
        modifier = Modifier.background(Color.White),
        cutoutShape = MaterialTheme.shapes.small.copy(
            CornerSize(percent = 50)
        ),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 30.dp,
            end = 20.dp,
            bottom = 20.dp
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
                .offset(x = (-10).dp, y = (-20).dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFC7C7C7))
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                content = {
                    OutlinedButton(
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(10.dp)
                            .offset(y = (1).dp),
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
                            .offset(y = (1).dp),
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

