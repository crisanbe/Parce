package com.gerotac.auth.updateuser.presentation.ui.updateUser.student

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Pin
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gerotac.auth.updateuser.presentation.state.DocumentNumberState
import com.gerotac.components_ui.componets.state.TextFieldValueState

@Composable
fun DocumentStudent(documentNumber : TextFieldValueState = remember { DocumentNumberState()}) {
    val focusManager = LocalFocusManager.current
    TextField(value = documentNumber.text,
        onValueChange = {
            if (it.length <= 10) documentNumber.text = it
        },
        label = { Text(stringResource(id = com.gerotac.auth.R.string.TextField_Id_number)) },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        modifier = Modifier.onFocusChanged { focusState ->
            documentNumber.onFocusedChange(focusState.isFocused)
            if (!focusState.isFocused) {
                documentNumber.enableShowErrors()
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        isError = documentNumber.showErros(),
        maxLines = 1,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Pin,
                contentDescription = stringResource(id = com.gerotac.auth.R.string.TextField_Id_number),
            )
            Divider(
                modifier = Modifier
                    .width(34.3.dp)
                    .height(30.dp)
                    .padding(start = 33.dp)
            )
        })
}