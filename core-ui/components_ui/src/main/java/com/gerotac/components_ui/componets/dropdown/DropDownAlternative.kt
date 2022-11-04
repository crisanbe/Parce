package com.gerotac.components_ui.componets.dropdown

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.gerotac.components_ui.componets.state.TextFieldValueState
import com.gerotac.components_ui.componets.state.Value

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownAlternative(
    ValueState: TextFieldValueState = remember { Value("") },
    text: String,
    options: List<String>,
    readOnly: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions(),
    mainIcon: Painter?
) {
    var expanded by remember { mutableStateOf(false) }
    var rotateIcon by remember { mutableStateOf(0f) }
    var errorVisibility by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
        expanded = !expanded
    }) {
        OutlinedTextField(
            value = ValueState.text,
            maxLines = 1,
            enabled = false,
            onValueChange = { ValueState.text = it },
            placeholder = { Text(text = text, color = Color.Black) },
            trailingIcon = {
                rotateIcon = if (expanded) 180f else 0f
                Image(
                    painter = mainIcon!!,
                    contentDescription = null,
                    modifier = Modifier.rotate(rotateIcon)
                )
            },
            keyboardActions = keyboardActions,
            readOnly = readOnly,
            interactionSource = MutableInteractionSource()

        )
        Box {
            ExposedDropdownMenu(
                expanded = expanded, onDismissRequest = {
                    expanded = false
                    errorVisibility = ValueState.text.isEmpty()
                }, modifier = Modifier.border(BorderStroke(2.dp, color = Color.Yellow))
            ) {
                options.forEachIndexed { index, selectionOption ->
                    DropdownMenuItem(
                        contentPadding = PaddingValues(horizontal = 15.dp), onClick = {
                            ValueState.text = selectionOption
                            expanded = false
                        }, modifier = Modifier.sizeIn(maxHeight = 40.dp)
                    ) {
                        Column {
                            Text(
                                text = options[index], color = Color.Black
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