package com.gerotac.auth.updateuser.domain.repository

import com.gerotac.auth.updateuser.data.remote.dto.ParameterUpdateUserDto
import com.gerotac.auth.updateuser.data.remote.dto.ReturnUpdateUserDto

interface UpdateUserRepository {
    suspend fun doUpdateUser(
        token: String,
        parameterUpdateUser: ParameterUpdateUserDto
    ): ReturnUpdateUserDto
}