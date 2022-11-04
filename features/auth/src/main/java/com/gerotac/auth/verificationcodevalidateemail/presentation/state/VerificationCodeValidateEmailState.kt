package com.gerotac.auth.verificationcodevalidateemail.presentation.state

import com.gerotac.auth.verificationcodevalidateemail.data.remote.dto.gettoken.VerificationCodeValidateEmailDto

data class VerificationCodeValidateEmailState(
    val isLoading: Boolean = false,
    val code: VerificationCodeValidateEmailDto? = null,
    val error: String = ""
)
