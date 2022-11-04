package com.gerotac.auth.newcode.domain.model

data class ReturnNewCode(
    val message: String,
    val state: Boolean,
    val status: String
)


