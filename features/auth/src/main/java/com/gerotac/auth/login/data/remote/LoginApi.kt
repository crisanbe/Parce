package com.gerotac.auth.login.data.remote

import com.gerotac.auth.login.data.remote.logindto.LoginDto
import com.gerotac.auth.login.data.remote.returnlogindto.ReturnLoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("auth/login")
    suspend fun doLogin(
        @Body login: LoginDto
    ): ReturnLoginDto
}
