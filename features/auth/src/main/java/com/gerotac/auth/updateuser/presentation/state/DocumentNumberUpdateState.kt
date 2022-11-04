package com.gerotac.auth.updateuser.presentation.state

import com.gerotac.components_ui.componets.state.TextFieldValueState

class DocumentNumberState
    : TextFieldValueState(
    validator = ::isNumberValid,
    errorFor = ::documentValidationError
)

class PhoneNumberState
    : TextFieldValueState(
    validator = ::isNumberValid,
    errorFor = ::phoneValidationError
)

private fun isNumberValid(number: String) = number.length in 6..10

@Suppress("UNUSED_PARAMETER")
private fun phoneValidationError(phone: String)= "*telefono required"

@Suppress("UNUSED_PARAMETER")
private fun documentValidationError(document: String)= "*Document-mayor a 6 #"