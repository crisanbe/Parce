package com.gerotac.auth.register.domain.model

import java.io.Serializable

data class Authorization(
    val expiration: Int?,
    val token: String?,
    val type: String
) : Serializable
