package com.gerotac.auth.intervention.getintervention.data.remote.api

import com.gerotac.auth.intervention.getintervention.data.remote.getdetailrequirementinterventions.RelationsDto
import com.gerotac.auth.requirement.data.remote.getdetailrequirement.DataDto
import com.gerotac.auth.requirement.data.remote.getrequirement.GetRequirementResponse
import com.gerotac.auth.requirement.data.remote.requirementsave.RequirementResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface GetInterventionApi {

    @GET("requierements/")
    suspend fun doGetInterventionApi(
        @Header("Authorization") token: String,
        @Query("requierementId") id: String? = null
    ): RelationsDto

    @GET("requierements/{id}")
    suspend fun doGetDetailInterventionApi(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DataDto
}