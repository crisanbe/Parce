package com.gerotac.auth.register.data.remote

import com.gerotac.auth.register.data.remote.dto.RegisterDto
import com.gerotac.auth.register.data.remote.dto.TokenRegisterDto
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("auth/signup")
    suspend fun doRegister(
        @Body register: RegisterDto
    ): TokenRegisterDto
}
