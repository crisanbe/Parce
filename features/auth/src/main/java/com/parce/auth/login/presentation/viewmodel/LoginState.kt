package com.parce.auth.login.presentation.viewmodel

import com.parce.auth.login.data.remote.returnlogindto.ReturnLoginDto

data class LoginState(
    val isLoading: Boolean = false,
    val token: ReturnLoginDto? = null,
    val error: String = "",
    val message: String = ""
)
