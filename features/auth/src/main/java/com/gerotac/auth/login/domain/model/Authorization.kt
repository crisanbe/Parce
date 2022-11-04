package com.gerotac.auth.login.domain.model

import java.io.Serializable

data class Authorization(
    val expiration: Int,
    val token: String,
    val type: String
) : Serializable
