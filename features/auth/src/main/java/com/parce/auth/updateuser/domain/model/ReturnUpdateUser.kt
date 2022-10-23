package com.parce.auth.updateuser.domain.model

import java.io.Serializable

data class ReturnUpdateUser(
    val message: String,
    val status: String,
    val user: ReturnUser
) : Serializable