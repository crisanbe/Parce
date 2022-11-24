package com.gerotac.auth.updaterequirement.data.remote

import com.gerotac.auth.updaterequirement.data.remote.dto.request.ResquestUpdateRequirement
import com.gerotac.auth.updaterequirement.data.remote.dto.response.ResponseUpdateRequirementDto
import com.gerotac.auth.updateuser.data.remote.dto.ParameterUpdateUserDto
import com.gerotac.auth.updateuser.data.remote.dto.ReturnUpdateUserDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
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