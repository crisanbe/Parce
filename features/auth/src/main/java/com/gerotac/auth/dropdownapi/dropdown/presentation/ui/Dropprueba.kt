package com.gerotac.auth.dropdownapi.dropdown.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
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
import com.gerotac.auth.requirement.presentation.ui.homerequirement.listrequirement.HomeRequirements

@SuppressLint("SuspiciousIndentation")
@Composable
fun DropPrueba(
    selectOptionChange: (Int) -> Unit,
    text: String,
    options: List<ResultStudent>,
    mainIcon: (Painter?)
) {
    var expanded by remember { mutableStateOf(false) }
    var selectOption by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown

    Column(modifier = Modifier.width(300.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectOption,
                maxLines = 1,
                onValueChange = {
                    selectOption = it
                    expanded = true
                },
                placeholder = {
                    Text(
                        text = text,
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold,
                    )
                },
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        //This value is used to assign to the DropDown the same width
                        textfieldSize = coordinates.size.toSize()
                    },
                label = { Text("Estudiante") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { expanded = !expanded })
                }
            )
        }
        AnimatedVisibility(visible = expanded) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .width(textfieldSize.width.dp),
                elevation = 15.dp,
                shape = RoundedCornerShape(10.dp)
            ) {
                options.forEachIndexed { index, selectionOption ->
                    DropdownMenuItem(
                        contentPadding = PaddingValues(horizontal = 15.dp), onClick = {
                            selectOption = selectionOption.name
                            expanded = false
                            selectOptionChange(selectionOption.id)
                        }, modifier = Modifier.sizeIn(maxHeight = 40.dp)
                    )
                    {
                        Column() {
                            if (selectOption.isNotEmpty()) {
                                options.filter {
                                    options[index].name.lowercase()
                                        .contains(selectOption.lowercase()) || options[index].name
                                        .lowercase()
                                        .contains("others")
                                }
                                    .sortedBy {
                                        selectOption.lowercase()
                                    }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                ) {
                                    Text(text = options[index].name, fontSize = 16.sp)
                                }
                            }else {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                ) {
                                    Text(text = options[index].name, fontSize = 16.sp)
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
fun CategoryItems(
    iem: ResultStudent,
    onSelect: (String) -> Unit
) {



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