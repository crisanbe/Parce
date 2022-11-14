package com.gerotac.auth.dropdownapi.dropdown.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.gerotac.auth.dropdownapi.dropdown.domain.model.dropmodel.Result

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropAcademic(
    selectOptionChange: (Int) -> Unit,
    text: String,
    options: List<Result>,
    mainIcon: (Painter?)
) {
    var selectOption by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var rotateIcon by remember { mutableStateOf(0f) }
    var errorVisibility by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
        expanded = !expanded
    },
        modifier = Modifier.wrapContentSize()
    ) {
        OutlinedTextField(
            value = selectOption,
            maxLines = 1,
            onValueChange = {},
            enabled = false,
            placeholder = { Text(text = text, color = Color.Black) },
            trailingIcon = {
                rotateIcon = if (expanded) 180f else 0f
                if (mainIcon != null) {
                    Image(
                        painter = mainIcon,
                        contentDescription = null,
                        modifier = Modifier.rotate(rotateIcon)
                    )
                }
            })
        Box {
            ExposedDropdownMenu(
                expanded = expanded, onDismissRequest = {
                    expanded = false
                    errorVisibility = selectOption.isEmpty()
                }, modifier = Modifier.border(BorderStroke(2.dp, color = Color.Yellow))
            ) {
                options.forEachIndexed { index, selectionOption ->
                    DropdownMenuItem(
                        contentPadding = PaddingValues(horizontal = 15.dp), onClick = {
                            selectOption = selectionOption.name
                            expanded = false
                            selectOptionChange(selectionOption.id)
                        }, modifier = Modifier.sizeIn(maxHeight = 40.dp)
                    ) {
                        Column {
                            Text(
                                text = options[index].name, color = Color.Black
                            )
                            if (index != options.lastIndex) {
                                Spacer(modifier = Modifier.size(10.dp))
                                Divider(
                                    thickness = 2.dp, color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}