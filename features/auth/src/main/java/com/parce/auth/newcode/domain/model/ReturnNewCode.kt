package com.parce.auth.newcode.domain.model

data class ReturnNewCode(
    val message: String,
    val state: Boolean,
    val status: String
)


