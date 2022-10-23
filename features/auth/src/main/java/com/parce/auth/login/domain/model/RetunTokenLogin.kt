package com.parce.auth.login.domain.model

import java.io.Serializable

data class RetunTokenLogin(
    val authorization: Authorization,
    val code: String,
    val message: String,
    val state: Boolean,
    val status: String,
    val user: User
) : Serializable
