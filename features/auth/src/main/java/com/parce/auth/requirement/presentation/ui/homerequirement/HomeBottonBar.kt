package com.parce.auth.requirement.presentation.ui.homerequirement

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
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
import com.parce.auth.login.presentation.components.logincomposables.userRepo
import com.parce.components_ui.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun HomeBottomBar(
    showPrevious: Boolean,
    showNext: Boolean,
    onPreviousPressed: () -> Unit,
    onNextPressed: () -> Unit,
    cutoutShape: Shape? = null,
) {
    Surface(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD3D3D3))
                .padding(horizontal = 10.dp, vertical = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = {
                OutlinedButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .padding(horizontal = 15.dp)
                        .offset(y = (-5).dp),
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
                                Color(0xFFFFFFFF),
                                Color(0xFFFFFFFF)
                            )
                        )
                    )
                ) {
                    Text(
                        text = stringResource(id = com.parce.auth.R.string.previous_button),
                        style = androidx.compose.ui.text.TextStyle(
                            color = colorResource(id = R.color.primary)
                        ),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                OutlinedButton(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .padding(horizontal = 15.dp)
                        .offset(y = (-5).dp),
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
                                Color(0xFFFFFFFF),
                                Color(0xFFFFFEFC)
                            )
                        )
                    )
                ) {
                    Text(
                        text = stringResource(id = com.parce.auth.R.string.next_button),
                        style = TextStyle(
                            color = colorResource(id = R.color.primary)
                        ),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            })
    }
}