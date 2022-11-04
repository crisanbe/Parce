package com.gerotac.auth.newpasswordforget.presentation.viewmodel

import com.gerotac.auth.newpasswordforget.data.remote.dto.ReturnNewPasswordForgetDto

data class NewPasswordForgetState(
    val isLoading: Boolean = false,
    val newPasswordForgetDto: ReturnNewPasswordForgetDto? = null,
    val error : String = ""
)