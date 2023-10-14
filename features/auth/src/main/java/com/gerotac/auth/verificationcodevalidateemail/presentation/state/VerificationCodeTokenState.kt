package com.gerotac.auth.verificationcodevalidateemail.presentation.state

import com.gerotac.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.ReturnConfirmationCodeTokenDto

data class VerificationCodeTokenState(
    val isLoading: Boolean = false,
    val code: ReturnConfirmationCodeTokenDto? = null,
    val error: String = ""
)
