package com.gerotac.auth.login.presentation.components.logincomposables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.gerotac.auth.R
import com.gerotac.components_ui.componets.DividerIcon

@Composable
fun Password(nameError: Boolean, password: String, onTextChanged: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(true) }
    TextField(value = password,
        onValueChange = {
            if (it.length <= 15) onTextChanged(it)
            !nameError
        },
        label = { Text(stringResource(R.string.TextField_Password)) },
        singleLine = true,
        maxLines = 1,
        isError = nameError,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = stringResource(R.string.TextField_Password),
                tint = Color.Black
            )
            DividerIcon()
        },
        visualTransformation = if (passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                if (passwordVisibility) {
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
    Spacer(Modifier.size(5.dp))
}