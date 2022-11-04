package com.gerotac.auth.validateCodeVerificationForgotPassword.presentation.viewModel

import com.gerotac.auth.validateCodeVerificationForgotPassword.data.remote.dto.ReturnCodeForgotPasswordDto

data class CodeForgotPasswordState(
    val isLoading: Boolean = false,
    val codeForgotPasswordDto: ReturnCodeForgotPasswordDto? = null,
    val error : String = ""
)