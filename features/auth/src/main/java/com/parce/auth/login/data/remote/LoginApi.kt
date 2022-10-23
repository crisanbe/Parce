package com.parce.auth.login.data.remote

import com.parce.auth.login.data.remote.logindto.LoginDto
import com.parce.auth.login.data.remote.returnlogindto.ReturnLoginDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApi {
    @POST("auth/login")
    suspend fun doLogin(
        @Body login: LoginDto
    ): ReturnLoginDto
}
