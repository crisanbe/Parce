package com.gerotac.auth.updateuser.data.repository

import com.gerotac.auth.updateuser.data.remote.UpdateUserApi
import com.gerotac.auth.updateuser.data.remote.dto.ParameterUpdateUserRequest
import com.gerotac.auth.updateuser.data.remote.dto.ReturnUpdateUserDto
import com.gerotac.auth.updateuser.domain.repository.UpdateUserRepository
import javax.inject.Inject


class UpdateUserRepositoryImpl @Inject constructor(private val api: UpdateUserApi) :
    UpdateUserRepository {
    override suspend fun doUpdateUser(
        token: String,
        parameterUpdateUser: ParameterUpdateUserRequest
    ): ReturnUpdateUserDto {
        return api.doUpdateUser(token = token, parameterUpdateUser)
    }
}
