package com.gerotac.auth.register.presentation.state

import com.gerotac.auth.register.data.remote.dto.TokenRegisterDto

data class RegisterState(
    val isLoading: Boolean = false,
    val token: TokenRegisterDto? = null,
    val error: String = "",
    val message: String = ""
)

/*
data class Errors(
    val email: List<String>
)*/
