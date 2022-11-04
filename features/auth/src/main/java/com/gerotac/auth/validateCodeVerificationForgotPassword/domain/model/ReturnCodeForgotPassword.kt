package com.gerotac.auth.validateCodeVerificationForgotPassword.domain.model

data class ReturnCodeForgotPassword(
    val message: String,
    val state: Boolean,
    val status: String,
    val token: String
)
