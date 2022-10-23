package com.parce.auth.codeverificationRegister.presentation.viewmodel

import com.parce.auth.codeverificationRegister.data.remote.dto.ReturnConfirmationDto

data class VerificationCodeState(
    val isLoading: Boolean = false,
    val code: ReturnConfirmationDto? = null,
    val error: String = ""
)
