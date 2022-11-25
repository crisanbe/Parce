package com.gerotac.auth.profileUser.data.remote.api

import com.gerotac.auth.profileUser.data.remote.response.respuesta.ResponseProfileDto
import retrofit2.http.GET
import retrofit2.http.Header

interface GetProfileUserApi {
    @GET("auth/user-profile")
    suspend fun doGetProfile(
        @Header("Authorization") token: String
    ): ResponseProfileDto
}
