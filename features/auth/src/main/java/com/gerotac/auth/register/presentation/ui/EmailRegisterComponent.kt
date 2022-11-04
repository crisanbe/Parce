package com.gerotac.auth.register.presentation.ui

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.gerotac.auth.R.*
import com.gerotac.components_ui.componets.DividerIcon
import com.gerotac.components_ui.componets.state.TextFieldValueState
import com.gerotac.components_ui.componets.state.ValueState

@Composable
fun EmailRegister(emailState: TextFieldValueState = remember { ValueState() }) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = emailState.text,
        onValueChange = { emailState.text = it },
        label = {
            CompositionLocalProvider(values = arrayOf(LocalContentAlpha provides ContentAlpha.medium)) {
                Text(
                    stringResource(string.TextField_Email_address),
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }
        },
        modifier = Modifier
            .width(300.dp)
            .onFocusChanged { focusState ->
                emailState.onFocusedChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    emailState.enableShowErrors()
                }
            },
        textStyle = MaterialTheme.typography.body2,
        isError = emailState.showErros(),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions (
            onDone = { focusManager.moveFocus(FocusDirection.Down) }),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = stringResource(string.TextField_Email_address),
                tint = Color.Black
            )
            DividerIcon()
        }
    )
}