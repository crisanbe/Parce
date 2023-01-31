package com.gerotac.auth.requirement.presentation.ui.homerequirement.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter

@Composable
fun FormValueComp(
    icon: AsyncImagePainter? = null,
    ValueState: (String) -> Unit,
    text: String?,
    valueText: String? = null
) {
    TextField(
        modifier = Modifier.widthIn(350.dp),
        value = text ?: "",
        onValueChange = { ValueState(it) },
        label = { Text(valueText.toString()) },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        singleLine = true,
        maxLines = 1,
        readOnly = true,
        leadingIcon = {
            icon?.let {
                Icon(
                    painter = it,
                    contentDescription = text,
                    tint = Color.Black
                )
            }
            Divider(
                modifier = Modifier
                    .width(34.3.dp)
                    .height(30.dp)
                    .padding(start = 33.dp)
            )
        })
}
