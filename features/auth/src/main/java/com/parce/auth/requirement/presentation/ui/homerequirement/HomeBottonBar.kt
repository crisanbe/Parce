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
        cutoutShape = MaterialTheme.shapes.small.copy(
            CornerSize(percent = 50)
        ),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 20.dp
        )
    ) {
        Surface(
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
                .offset(x = (-10).dp, y = (-20).dp)
                .background(Color.Transparent)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x00000000))
                    .padding(horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                content = {
                    OutlinedButton(
                        modifier = Modifier
                            .weight(3f)
                            .heightIn(20.dp)
                            .padding(horizontal = 10.dp)
                            .offset(y = (1).dp),
                        elevation = ButtonDefaults.elevation(defaultElevation = 10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFF0A090A)
                        ),
                        enabled = showPrevious,
                        onClick = onPreviousPressed,
                        border = BorderStroke(
                            width = 2.dp,
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color(0xFF050303),
                                    Color(0xFF000000)
                                )
                            )
                        )
                    ) {
                        Icon(
                            Icons.Filled.SkipPrevious,
                            contentDescription = "Anterior",
                            tint= Color.Black,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                    }
                    OutlinedButton(
                        modifier = Modifier
                            .weight(3f)
                            .heightIn(30.dp)
                            .padding(horizontal = 10.dp)
                            .offset(y = (1).dp),
                        elevation = ButtonDefaults.elevation(defaultElevation = 10.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFF0D090E)
                        ),
                        enabled = showNext,
                        onClick = onNextPressed,
                        border = BorderStroke(
                            width = 2.dp,
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color(0xFF000000),
                                    Color(0xFF000000)
                                )
                            )
                        )
                    ) {
                        Icon(
                            Icons.Filled.SkipNext,
                            contentDescription = "Archivo",
                            tint= Color.Black,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                    }
                })
        }
    }
}

