package com.parce.auth.updateuser.domain.repository

import com.parce.auth.updateuser.data.remote.dto.ParameterUpdateUserDto
import com.parce.auth.updateuser.data.remote.dto.ReturnUpdateUserDto

interface UpdateUserRepository {
    suspend fun doUpdateUser(
        token: String,
        parameterUpdateUser: ParameterUpdateUserDto
    ): ReturnUpdateUserDto
}