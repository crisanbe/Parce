package com.parce.auth.register.domain.model

import java.io.Serializable

data class RetunTokenRegister(
    val authorization: Authorization,
    val code: Int,
    val message: String,
    val state: Boolean,
    val status: String,
    val user: User
) : Serializable
