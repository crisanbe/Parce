package com.gerotac.auth.requirement.data.remote.api

import com.gerotac.auth.requirement.data.remote.getdetailrequirement.DataDto
import com.gerotac.auth.requirement.data.remote.getrequirement.GetRequirementResponse
import com.gerotac.auth.requirement.data.remote.requirementsave.RequirementResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface RequirementApi {

    @Multipart
    @POST("requierements")
    suspend fun doRequirementApi(
        @Header("Authorization") token: String,
        @Part("area_intervention") area_intervention: Int,
        @Part("description") description: RequestBody,
        @Part("cause_problem") cause_problem: RequestBody,
        @Part("efect_problem") efect_problem: RequestBody,
        @Part("impact_problem") impact_problem: RequestBody,
        @Part document: MutableList<MultipartBody.Part>
    ): RequirementResponse


    @GET("requierements/")
    suspend fun doGetRequirementApi(
        @Header("Authorization") token: String,
        @Query("page") current_page: Int? = null,
        @Query("requierementId") id: String? = null
    ): GetRequirementResponse

    @GET("requierements/{id}")
    suspend fun doGetDetailRequirementApi(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DataDto

}