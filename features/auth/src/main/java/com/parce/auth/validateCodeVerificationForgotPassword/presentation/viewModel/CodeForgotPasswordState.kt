package com.parce.auth.validateCodeVerificationForgotPassword.presentation.viewModel

import com.parce.auth.newcode.data.remote.dto.ReturnNewCodeDto
import com.parce.auth.validateCodeVerificationForgotPassword.data.remote.dto.ReturnCodeForgotPasswordDto

data class CodeForgotPasswordState(
    val isLoading: Boolean = false,
    val codeForgotPasswordDto: ReturnCodeForgotPasswordDto? = null,
    val error : String = ""
)