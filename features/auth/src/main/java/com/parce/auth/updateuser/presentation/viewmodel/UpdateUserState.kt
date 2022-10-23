package com.parce.auth.updateuser.presentation.viewmodel

import com.parce.auth.updateuser.data.remote.dto.ReturnUpdateUserDto

data class UpdateUserState(
    val isLoading: Boolean = false,
    val parameterUpdateUser: ReturnUpdateUserDto? = null,
    val error: String = "",
    val message: String = "",
)
