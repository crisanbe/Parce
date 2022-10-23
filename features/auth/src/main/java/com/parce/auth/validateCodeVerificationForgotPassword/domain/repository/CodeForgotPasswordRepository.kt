package com.parce.auth.validateCodeVerificationForgotPassword.domain.repository

import com.parce.auth.newcode.data.remote.dto.ReturnNewCodeDto
import com.parce.auth.validateCodeVerificationForgotPassword.data.remote.dto.ParameterCodeForgotPasswordDto
import com.parce.auth.validateCodeVerificationForgotPassword.data.remote.dto.ReturnCodeForgotPasswordDto

interface CodeForgotPasswordRepository {
    suspend fun doCodeForgotPassword(token: ParameterCodeForgotPasswordDto) : ReturnCodeForgotPasswordDto
}
