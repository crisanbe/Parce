package com.gerotac.auth.register.domain.model

data class Register(
    val email: String,
    val name: String,
    val password: String,
    val password_confirmation: String,
    val role: String
)
