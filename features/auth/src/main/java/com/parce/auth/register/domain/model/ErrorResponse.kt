package com.parce.auth.register.domain.model

data class ErrorResponse(
    val errors: Errors,
    val message: String
)