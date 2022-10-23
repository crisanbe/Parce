package com.parce.auth.login.presentation.components.logincomposables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IncomeComponent(modifier: Modifier) {
    Column(
    modifier = modifier
    .fillMaxWidth()
    .padding(start = 55.dp),
    verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(com.parce.auth.R.string.Text_Income),
            fontSize = 25.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(com.parce.auth.R.string.Text_Welcome_back),
            fontSize = 14.sp,
            color = Color.Gray,
        )
    }
}