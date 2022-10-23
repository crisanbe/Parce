package com.parce.auth.newpasswordforget.presentation.viewmodel

import com.parce.auth.newpasswordforget.data.remote.dto.ReturnNewPasswordForgetDto

data class NewPasswordForgetState(
    val isLoading: Boolean = false,
    val newPasswordForgetDto: ReturnNewPasswordForgetDto? = null,
    val error : String = ""
)