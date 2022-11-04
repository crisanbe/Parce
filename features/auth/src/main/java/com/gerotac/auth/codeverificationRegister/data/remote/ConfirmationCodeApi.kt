package com.gerotac.auth.codeverificationRegister.data.remote

import com.gerotac.auth.codeverificationRegister.data.remote.dto.ParameterCodeConfirmationDto
import com.gerotac.auth.codeverificationRegister.data.remote.dto.ReturnConfirmationDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ConfirmationCodeApi {
    @POST("code/verify")
    suspend fun doConfirmationCode(
        @Header("Authorization") token: String,
        @Body confirmationCode: ParameterCodeConfirmationDto
    ): ReturnConfirmationDto
}
