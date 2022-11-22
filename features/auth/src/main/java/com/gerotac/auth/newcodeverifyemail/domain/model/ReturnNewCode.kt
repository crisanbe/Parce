package com.gerotac.auth.newcodeverifyemail.domain.model

data class ReturnNewCode(
    val message: String,
    val state: Boolean,
    val status: String
)


