package com.gerotac.auth.newpasswordforget.domain.repository

import com.gerotac.auth.newpasswordforget.data.remote.dto.NewPasswordForgetDto
import com.gerotac.auth.newpasswordforget.data.remote.dto.ReturnNewPasswordForgetDto

interface NewPasswordForgetRepository {
    suspend fun doNewPasswordForget(newPasswordForget: NewPasswordForgetDto)
    : ReturnNewPasswordForgetDto
}