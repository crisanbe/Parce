package com.gerotac.auth.register.domain.model

data class ErrorResponse(
    val errors: Errors,
    val message: String
)