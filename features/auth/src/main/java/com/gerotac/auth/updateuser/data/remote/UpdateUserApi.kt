package com.gerotac.auth.updateuser.data.remote

import com.gerotac.auth.updateuser.data.remote.dto.ParameterUpdateUserDto
import com.gerotac.auth.updateuser.data.remote.dto.ReturnUpdateUserDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UpdateUserApi {
    @POST("auth/user-profile")
    suspend fun doUpdateUser(
        @Header("Authorization") token: String,
        @Body updateUser: ParameterUpdateUserDto
    ): ReturnUpdateUserDto
}