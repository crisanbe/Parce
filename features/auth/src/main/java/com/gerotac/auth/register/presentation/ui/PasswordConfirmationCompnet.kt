package com.gerotac.auth.register.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gerotac.auth.R
import com.gerotac.auth.register.presentation.state.PasswordState
import com.gerotac.components_ui.componets.DividerIcon
import com.gerotac.components_ui.componets.state.TextFieldValueState

@Composable
fun PasswordConfirmation(passwordConfirmationState: TextFieldValueState = remember { PasswordState() }) {
    val focusManager = LocalFocusManager.current
    var hidden by remember { mutableStateOf(true) }
    TextField(
        value = passwordConfirmationState.text,
        onValueChange = { passwordConfirmationState.text = it },
        label = {
            Text(
                stringResource(R.string.TextField_Confirm_Password),
                color = Color.Black
            )
        },
        modifier = Modifier
            .widthIn(350.dp)
            .onFocusChanged { focusState ->
                passwordConfirmationState.onFocusedChange(focusState.isFocused)
                if (!focusState.isFocused) {
                    passwordConfirmationState.enableShowErrors()
                }
            },
        textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = stringResource(R.string.TextField_Confirm_Password),
                tint = Color.Black
            )
            DividerIcon()
        },
        visualTransformation = if (hidden) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            IconButton(onClick = { hidden = !hidden }) {
                if (hidden) {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = null,
                        tint = Color.Black,
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.VisibilityOff,
                        contentDescription = null,
                        tint = Color.Black,
                    )
                }
            }
        }
    )
    val assistiveElementText = "* Obligatorio mínimo 6 caracteres"
    val assistiveElementColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
    Text(
        text = assistiveElementText,
        color = assistiveElementColor,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.offset(x = (-1).dp)
    )
    Spacer(Modifier.size(5.dp))
}