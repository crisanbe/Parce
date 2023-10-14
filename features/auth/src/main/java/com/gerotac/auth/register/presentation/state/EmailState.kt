package com.gerotac.auth.register.presentation.state

import com.gerotac.components_ui.componets.state.TextFieldValueState
import java.util.regex.Pattern

private const val EMAIL_VALIDATOR_REGEX = "^(.+)@(.+)\$"

class EmailState : TextFieldValueState(validator = ::isEmailValid, errorFor = ::emailValidationError)

private  fun isEmailValid(email: String) = Pattern.matches(EMAIL_VALIDATOR_REGEX, email)

private fun emailValidationError(email: String) = "Invalid Email: $email"