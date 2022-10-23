package com.parce.auth.updateuser.presentation.ui.updateUser.student

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhoneAndroid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.parce.auth.R
import com.parce.auth.updateuser.presentation.state.PhoneNumberState
import com.parce.components_ui.componets.state.TextFieldValueState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PhoneStudentProfile(phoneState: TextFieldValueState = remember { PhoneNumberState() }) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(value = phoneState.text,
        onValueChange = { phoneState.text = it },
        label = { Text(stringResource(id = R.string.TextField_Phone)) },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        modifier = Modifier.onFocusChanged { focusState ->
            phoneState.onFocusedChange(focusState.isFocused)
            if (!focusState.isFocused) {
                phoneState.enableShowErrors()
            }
        },
        isError = phoneState.showErros(),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done, keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.PhoneAndroid,
                contentDescription = stringResource(id = R.string.TextField_Phone),
            )
            Divider(
                modifier = Modifier
                    .width(34.3.dp)
                    .height(30.dp)
                    .padding(start = 33.dp)
            )
        })
}