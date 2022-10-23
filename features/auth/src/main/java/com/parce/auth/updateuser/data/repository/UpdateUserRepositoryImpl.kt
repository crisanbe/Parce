package com.parce.auth.updateuser.data.repository

import com.parce.auth.updateuser.data.remote.UpdateUserApi
import com.parce.auth.updateuser.data.remote.dto.ParameterUpdateUserDto
import com.parce.auth.updateuser.data.remote.dto.ReturnUpdateUserDto
import com.parce.auth.updateuser.domain.repository.UpdateUserRepository
import javax.inject.Inject


class UpdateUserRepositoryImpl @Inject constructor(private val api: UpdateUserApi) :
    UpdateUserRepository {
    override suspend fun doUpdateUser(
        token: String,
        parameterUpateUser: ParameterUpdateUserDto
    ): ReturnUpdateUserDto {
        return api.doUpdateUser(token = token, parameterUpateUser)
    }
}
