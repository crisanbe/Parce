package com.gerotac.auth.newpasswordforget.data.repository

import com.gerotac.auth.newpasswordforget.data.remote.NewPasswordForgetApi
import com.gerotac.auth.newpasswordforget.data.remote.dto.NewPasswordForgetDto
import com.gerotac.auth.newpasswordforget.data.remote.dto.ReturnNewPasswordForgetDto
import com.gerotac.auth.newpasswordforget.domain.repository.NewPasswordForgetRepository
import javax.inject.Inject

class NewPasswordForgetRepositoryImpl @Inject constructor(private val api: NewPasswordForgetApi) :
    NewPasswordForgetRepository {
    override suspend fun doNewPasswordForget(newPasswordForget: NewPasswordForgetDto)
            : ReturnNewPasswordForgetDto {
        return api.doNewPasswordForget(newPasswordForgetDto = newPasswordForget)
    }
}
