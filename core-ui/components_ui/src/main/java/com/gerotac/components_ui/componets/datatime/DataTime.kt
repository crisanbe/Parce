package com.gerotac.components_ui.componets.datatime

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gerotac.components_ui.componets.state.TextFieldValueState
import com.gerotac.components_ui.componets.state.ValueState
import java.util.*


@Composable
fun DataTime(dateState: TextFieldValueState = remember { ValueState() }) {
    val focusManager = LocalFocusManager.current
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    var month = c.get(Calendar.MONTH)
    var day = c.get(Calendar.DAY_OF_MONTH)
    val context = LocalContext.current

    val datePickerDialog = DatePickerDialog(
        context, com.gerotac.components_ui.R.style.DialogTheme, { d, year1, month1, day1 ->
            month = month1 + 1
            day = day1
            var formattedMonth = "" + month
            var formattedDayOfMonth = "" + day
            if (month < 10) {
                formattedMonth = "0$month";
            }
            if (day < 10) {
                formattedDayOfMonth = "0$day";
            }
            dateState.text = "$formattedDayOfMonth-$formattedMonth-$year1"
        }, year, month, day
    )

    TextField(
        value = dateState.text,
        enabled = false,
        onValueChange = { dateState.text = it },
        label = { Text("Cumpleaños") },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        leadingIcon = {
            Icon(
                modifier = Modifier.clickable { datePickerDialog.show() },
                painter = painterResource(id = com.gerotac.components_ui.R.drawable.calendar_month),
                contentDescription = "",
                tint = Color.Black
            )
            Divider(
                modifier = Modifier
                    .width(34.3.dp)
                    .height(30.dp)
                    .padding(start = 33.dp)
            )
        }
    )
}