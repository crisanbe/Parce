package com.gerotac.auth.login.presentation.viewmodel

import com.gerotac.auth.login.data.remote.returnlogindto.ReturnLoginDto

data class LoginState(
    val isLoading: Boolean = false,
    val token: ReturnLoginDto? = null,
    val error: String = "",
    val message: String = ""
)
