package com.parce.auth.validateCodeVerificationForgotPassword.domain.model

import com.google.gson.annotations.SerializedName
import com.parce.auth.newcode.domain.model.ReturnNewCode

data class ReturnCodeForgotPassword(
    val message: String,
    val state: Boolean,
    val status: String,
    val token: String
)
