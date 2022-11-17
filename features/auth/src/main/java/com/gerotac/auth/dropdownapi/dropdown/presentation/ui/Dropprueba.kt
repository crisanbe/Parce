package com.gerotac.auth.dropdownapi.dropdown.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import coil.size.Size
import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.ResultArea
import com.gerotac.auth.dropdownapi.dropdown.domain.model.studentbyarea.ResultStudent
import com.gerotac.components_ui.R

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
    var selectedText by remember { mutableStateOf(text) }

    var textfieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown

        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text("Label") },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )
        Box {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
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
                                text = options[index].name,
                                color = Color.Black,
                                fontWeight = FontWeight.ExtraBold,
                                style = androidx.compose.ui.text.TextStyle(
                                    color = colorResource(id = R.color.black)
                                ),
                            )
                        }
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