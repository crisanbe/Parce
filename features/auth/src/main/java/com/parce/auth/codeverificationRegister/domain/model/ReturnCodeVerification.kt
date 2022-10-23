package com.parce.auth.codeverificationRegister.domain.model

data class ReturnCodeVerification(
    val message: String,
    val state: Boolean,
    val status: String
)


