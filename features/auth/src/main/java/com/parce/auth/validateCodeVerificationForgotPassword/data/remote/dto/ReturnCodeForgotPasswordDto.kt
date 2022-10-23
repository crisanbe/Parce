package com.parce.auth.validateCodeVerificationForgotPassword.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.parce.auth.validateCodeVerificationForgotPassword.domain.model.ReturnCodeForgotPassword

data class ReturnCodeForgotPasswordDto(
    @SerializedName("message") val message: String,
    @SerializedName("state") val state: Boolean,
    @SerializedName("status") val status: String,
    @SerializedName("token") val token: String
)

fun ReturnCodeForgotPasswordDto.toReturnCodeForgotPassword(): ReturnCodeForgotPassword {
    return ReturnCodeForgotPassword(
        message = message,
        state = state,
        status = status,
        token = token
    )
}