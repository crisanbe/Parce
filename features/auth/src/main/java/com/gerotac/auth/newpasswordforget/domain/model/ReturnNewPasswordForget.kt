package com.gerotac.auth.newpasswordforget.domain.model

data class ReturnNewPasswordForget(
    val state: String,
    val status: String,
    val message: String
)


