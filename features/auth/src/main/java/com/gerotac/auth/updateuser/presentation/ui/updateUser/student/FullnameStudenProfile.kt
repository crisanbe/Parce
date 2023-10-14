package com.gerotac.auth.updateuser.presentation.ui.updateUser.student

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Feed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.gerotac.auth.R
import com.gerotac.components_ui.componets.state.TextFieldValueState
import com.gerotac.components_ui.componets.state.ValueState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FullNameStudent(fullName: TextFieldValueState = remember { ValueState() }) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = fullName.text,
        onValueChange = { fullName.text = it },
        label = { Text(stringResource(id = R.string.TextField_full_name)) },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Feed,
                contentDescription = stringResource(id = R.string.TextField_Company_name),
            )
            Divider(
                modifier = Modifier
                    .width(34.3.dp)
                    .height(30.dp)
                    .padding(start = 33.dp)
            )
        })
}