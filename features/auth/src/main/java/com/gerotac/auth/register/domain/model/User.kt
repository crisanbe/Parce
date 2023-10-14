package com.gerotac.auth.register.domain.model

import java.io.Serializable

data class User(
    val created_at: String,
    val email: String,
    val id: Int,
    val name: String,
    val role: String,
    val updated_at: String
) : Serializable
