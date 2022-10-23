package com.parce.auth.validateCodeVerificationForgotPassword.data.repository

import com.parce.auth.validateCodeVerificationForgotPassword.data.remote.ValidateCodeVerificationForgotPasswordApi
import com.parce.auth.validateCodeVerificationForgotPassword.data.remote.dto.ParameterCodeForgotPasswordDto
import com.parce.auth.validateCodeVerificationForgotPassword.data.remote.dto.ReturnCodeForgotPasswordDto
import com.parce.auth.validateCodeVerificationForgotPassword.domain.repository.CodeForgotPasswordRepository
import javax.inject.Inject

class ValidateCodeVerificationForgotPasswordImpl @Inject constructor(
    private val api: ValidateCodeVerificationForgotPasswordApi
) :
    CodeForgotPasswordRepository {
    override suspend fun doCodeForgotPassword(token: ParameterCodeForgotPasswordDto):
            ReturnCodeForgotPasswordDto {
        return api.doCodeForgotPassword(codeForgotPassword = token)
    }
}
