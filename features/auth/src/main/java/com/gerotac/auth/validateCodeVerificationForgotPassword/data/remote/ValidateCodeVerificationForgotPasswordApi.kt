package com.gerotac.auth.validateCodeVerificationForgotPassword.data.remote

import com.gerotac.auth.validateCodeVerificationForgotPassword.data.remote.dto.ParameterCodeForgotPasswordDto
import com.gerotac.auth.validateCodeVerificationForgotPassword.data.remote.dto.ReturnCodeForgotPasswordDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ValidateCodeVerificationForgotPasswordApi {
    @POST("auth/password/code/check")
    suspend fun doCodeForgotPassword(
        @Body codeForgotPassword: ParameterCodeForgotPasswordDto
    ): ReturnCodeForgotPasswordDto
}