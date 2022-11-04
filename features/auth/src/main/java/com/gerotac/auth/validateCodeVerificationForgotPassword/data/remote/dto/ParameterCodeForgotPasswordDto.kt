package com.gerotac.auth.validateCodeVerificationForgotPassword.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ParameterCodeForgotPasswordDto(
    @SerializedName("token") val token: String
)