package com.gerotac.auth.newcodeverifyemail.data.remote

import com.gerotac.auth.newcodeverifyemail.data.remote.dto.ReturnNewCodeDto
import retrofit2.http.GET
import retrofit2.http.Header

interface NewCodeApi {
    @GET("email/new-code-verify")
    suspend fun doNewCode(
        @Header("Authorization") token: String
    ): ReturnNewCodeDto
}
