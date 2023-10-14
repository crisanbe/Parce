package com.gerotac.auth.newcode.data.remote

import com.gerotac.auth.newcode.data.remote.dto.ReturnNewCodeDto
import retrofit2.http.GET
import retrofit2.http.Header

interface NewCodeApi {
    @GET("email/new-code-verify")
    suspend fun doNewCode(
        @Header("Authorization") token: String
    ): ReturnNewCodeDto
}
