package com.gerotac.auth.validateCodeVerificationForgotPassword.domain.repository

import com.gerotac.auth.validateCodeVerificationForgotPassword.data.remote.dto.ParameterCodeForgotPasswordDto
import com.gerotac.auth.validateCodeVerificationForgotPassword.data.remote.dto.ReturnCodeForgotPasswordDto

interface CodeForgotPasswordRepository {
    suspend fun doCodeForgotPassword(token: ParameterCodeForgotPasswordDto) : ReturnCodeForgotPasswordDto
}
