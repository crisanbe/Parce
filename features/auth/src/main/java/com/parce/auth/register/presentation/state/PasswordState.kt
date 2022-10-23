package com.parce.auth.register.presentation.state

import com.parce.components_ui.componets.state.TextFieldValueState

class PasswordState
    : TextFieldValueState(validator = ::isPasswordValid, errorFor = ::passwordValidationError)


private fun isPasswordValid(password: String)= password.length > 4

@Suppress("UNUSED_PARAMETER")
private fun passwordValidationError(password: String)= "Invalid Password"