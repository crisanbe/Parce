package com.gerotac.components_ui.componets.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

 open class TextFieldValueState(
    private val validator: (String) -> Boolean = { true },
    private val errorFor: (String) -> String = { "" }
) {
    var text: String by mutableStateOf("")

    var isFocusedDirty: Boolean by mutableStateOf(false)
    var isfocused: Boolean by mutableStateOf(false)
    private var displayError: Boolean by mutableStateOf(false)


    open val isValid: Boolean
        get() = validator(text)

    fun onFocusedChange(focused: Boolean) {
        isfocused = focused
        if (focused) isFocusedDirty = true
    }

    fun enableShowErrors() {
        if (isFocusedDirty) {
            displayError = true
        }
    }

    fun showErros() = !isValid && displayError

    open fun getErros(): String? {
        return if (showErros()){
            errorFor(text)
        }else{
            null
        }
    }
}