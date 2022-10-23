package com.parce.auth.login.domain.model

import java.io.Serializable

data class User(
    val email: String,
    val name: String,
    val user_status: String,
    val email_verified: String,
    val role: String,
    val updated_at: String
) : Serializable
