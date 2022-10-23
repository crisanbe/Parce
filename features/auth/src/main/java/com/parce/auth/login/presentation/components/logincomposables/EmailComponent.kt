package com.parce.auth.login.presentation.components.logincomposables

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.parce.auth.R
import com.parce.components_ui.componets.DividerIcon

@Composable
fun Email(nameError: Boolean, email: String, onTextChanged: (String) -> Unit) {
    TextField(value = email,
        onValueChange = {
            if (it.length <= 25) onTextChanged(it)
            !nameError
        },
        label = { Text(stringResource(R.string.TextField_Email_address)) },
        isError = nameError,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = stringResource(id = R.string.TextField_Email_address),
                tint = Color.Black
            )
            DividerIcon()
        })
    val assistiveElementText = if (nameError) "Error: Obligatorio" else "* Obligatorio"
    val assistiveElementColor = if (nameError) {
        MaterialTheme.colors.error
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
    }

    Text(
        text = assistiveElementText,
        color = assistiveElementColor,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.offset(x = (-50).dp)
    )
}