package com.parce.auth.verificationcodevalidateemail.presentation.state

import com.parce.auth.verificationcodevalidateemail.data.remote.dto.gettoken.VerificationCodeValidateEmailDto

data class VerificationCodeValidateEmailState(
    val isLoading: Boolean = false,
    val code: VerificationCodeValidateEmailDto? = null,
    val error: String = ""
)
