package com.parce.auth.profileUser.data.remote.api

import com.parce.auth.profileUser.data.remote.response.ResponseProfileUser
import retrofit2.http.GET
import retrofit2.http.Header

interface GetProfileUserApi {
    @GET("auth/user-profile")
    suspend fun doGetProfile(
        @Header("Authorization") token: String
    ): ResponseProfileUser
}
