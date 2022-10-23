package com.parce.auth.verificationcodevalidateemail.presentation.state

import com.parce.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.ReturnConfirmationCodeTokenDto

data class VerificationCodeTokenState(
    val isLoading: Boolean = false,
    val code: ReturnConfirmationCodeTokenDto? = null,
    val error: String = ""
)
