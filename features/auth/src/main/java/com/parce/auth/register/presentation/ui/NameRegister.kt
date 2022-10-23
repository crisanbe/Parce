package com.parce.auth.register.presentation.ui

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.parce.auth.R
import com.parce.components_ui.componets.DividerIcon
import com.parce.components_ui.componets.state.TextFieldValueState
import com.parce.components_ui.componets.state.ValueState

@Composable
fun NameRegister(name: TextFieldValueState = remember {ValueState()}) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = name.text,
        onValueChange = { name.text = it },
        label = {
            Text(
                text = stringResource(R.string.TextField_Full_Name),
                color = Color.Black
            )
        },
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = stringResource(R.string.TextField_Full_Name),
                tint = Color.Black
            )
            DividerIcon()
        },
        modifier = Modifier.width(300.dp)
    )
}