package com.gerotac.components_ui.componets.dropdown

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.gerotac.components_ui.componets.DividerIcon
import com.gerotac.components_ui.componets.state.TextFieldValueState
import com.gerotac.components_ui.componets.state.ValueState

@Composable
fun DropDown(
    ValueState: TextFieldValueState = remember { ValueState() },
    text: String,
    contentList: List<String>,
    icon: ImageVector,
    icon1: ImageVector? = null,
    icon2: ImageVector? = null,
    mainIcon : (ImageVector?)
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    Box(contentAlignment = Alignment.BottomCenter) {
        TextField(
            value = ValueState.text,
            onValueChange = { ValueState.text = it },
            maxLines = 1,
            enabled = false,
            placeholder = {
                Text(
                    text = text,
                    color = Color.Black
                )
            },
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            leadingIcon = {
                if (ValueState.text.isEmpty()) {
                    Icon(
                        imageVector = mainIcon!!,
                        contentDescription = null,
                        tint = Color.Black
                    )
                } else {
                    when (contentList.indexOf(ValueState.text)) {
                        0 -> {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                        1 -> {
                            Icon(
                                imageVector = icon1!!,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                        2 -> {
                            Icon(
                                imageVector = icon2!!,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    }
                }
                DividerIcon()
            },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    if (expanded) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = null,
                            tint = Color.Black,
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = Color.Black,
                        )
                    }
                }
            },
            modifier = Modifier
                .width(280.dp)
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                })
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            contentList.forEach { label ->
                DropdownMenuItem(onClick = {
                    ValueState.text = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}