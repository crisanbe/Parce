package com.parce.auth.newpasswordforget.domain.repository

import com.parce.auth.newpasswordforget.data.remote.dto.NewPasswordForgetDto
import com.parce.auth.newpasswordforget.data.remote.dto.ReturnNewPasswordForgetDto

interface NewPasswordForgetRepository {
    suspend fun doNewPasswordForget(newPasswordForget: NewPasswordForgetDto)
    : ReturnNewPasswordForgetDto
}