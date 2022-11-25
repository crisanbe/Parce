package com.gerotac.auth.updaterequirement.data.remote

import com.gerotac.auth.updaterequirement.data.remote.dto.request.ResquestUpdateRequirement
import com.gerotac.auth.updaterequirement.data.remote.dto.response.ResponseUpdateRequirementDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface UpdateRequirementApi {
    @PUT("requierements/{id}")
    suspend fun doUpdateRequirement(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body updateRequirement: ResquestUpdateRequirement
    ): ResponseUpdateRequirementDto
}