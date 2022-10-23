package com.parce.auth.login.data.remote.logout

import retrofit2.http.POST

interface LogoutApi {
    @POST("auth/logout")
    suspend fun doLogin(): ReturnLogout
}