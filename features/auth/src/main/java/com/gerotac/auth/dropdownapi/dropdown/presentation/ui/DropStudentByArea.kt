package com.gerotac.auth.dropdownapi.dropdown.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.composed
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalAutofill
import androidx.compose.ui.platform.LocalAutofillTree
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.gerotac.auth.dropdownapi.dropdown.domain.model.studentbyarea.ResultStudent

@SuppressLint("SuspiciousIndentation")
@Composable
fun DropStudentByArea(
    selectOptionChange: (Int) -> Unit,
    text: String,
    options: List<ResultStudent>,
    mainIcon: (Painter?)
) {
    var expanded by remember { mutableStateOf(false) }
    var selectOption by remember { mutableStateOf("") }
    val heightTextFields by remember { mutableStateOf(55.dp) }
    var textFieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val icon = if (expanded)
        Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown


    Column(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                }
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heightTextFields)
                        .border(
                            width = 1.8.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .onGloballyPositioned { coordinates ->
                            //This value is used to assign to the DropDown the same width
                            textFieldSize = coordinates.size.toSize()
                        },
                    maxLines = 1,
                    value = selectOption,
                    onValueChange = {
                        selectOption = it
                        expanded = true
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    placeholder = {
                        Text(
                            text = text,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                        )
                    },
                    enabled = true,
                    label = { Text("Estudiante") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = "arrow",
                                tint = Color.Black
                            )
                        }
                    }
                )
            }
            AnimatedVisibility(visible = expanded) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(textFieldSize.width.dp),
                    elevation = 15.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    options.forEachIndexed { index, selectionOption ->
                        LazyColumn(
                            userScrollEnabled = false,
                            modifier = Modifier
                                .clickable {
                                    selectOption = selectionOption.name
                                    selectOptionChange(selectionOption.id)
                                    expanded = false
                                }
                                .heightIn(max = 150.dp),
                            contentPadding = PaddingValues(horizontal = 15.dp)
                        ) {
                            if (selectOption.isNotEmpty()) {
                                items(
                                    options.filter {
                                        it.name.lowercase()
                                            .contains(selectOption.lowercase()) || it.name.lowercase()
                                            .lowercase()
                                            .contains("others")
                                    }
                                        .sortedBy { it.name }
                                ) {
                                    StudentItems(name = it.name, id = it.id)
                                    { _, _ ->
                                        selectOption = it.name
                                        selectOptionChange(it.id)
                                        expanded = false
                                    }
                                }
                            } else {
                                items(
                                    options.sortedBy { it.name }
                                ) {
                                    StudentItems(name = it.name, id = it.id)
                                    { _, _ ->
                                        selectOption = it.name
                                        selectOptionChange(it.id)
                                        expanded = false
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StudentItems(
    name: String,
    id: Int,
    onSelect: (String, Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(name, id)
            }
            .padding(10.dp)
    ) {
        Text(text = name, fontSize = 16.sp)
    }

}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.autofill(
    autofillTypes: List<AutofillType>,
    onFill: ((String) -> Unit),
) = composed {
    val autofill = LocalAutofill.current
    val autofillNode = AutofillNode(onFill = onFill, autofillTypes = autofillTypes)
    LocalAutofillTree.current += autofillNode

    this
        .onGloballyPositioned {
            autofillNode.boundingBox = it.boundsInWindow()
        }
        .onFocusChanged { focusState ->
            autofill?.run {
                if (focusState.isFocused) {
                    requestAutofillForNode(autofillNode)
                } else {
                    cancelAutofillForNode(autofillNode)
                }
            }
        }
}