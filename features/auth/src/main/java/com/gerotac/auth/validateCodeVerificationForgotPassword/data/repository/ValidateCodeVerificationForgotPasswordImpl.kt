package com.gerotac.auth.validateCodeVerificationForgotPassword.data.repository

import com.gerotac.auth.validateCodeVerificationForgotPassword.data.remote.ValidateCodeVerificationForgotPasswordApi
import com.gerotac.auth.validateCodeVerificationForgotPassword.data.remote.dto.ParameterCodeForgotPasswordDto
import com.gerotac.auth.validateCodeVerificationForgotPassword.data.remote.dto.ReturnCodeForgotPasswordDto
import com.gerotac.auth.validateCodeVerificationForgotPassword.domain.repository.CodeForgotPasswordRepository
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
