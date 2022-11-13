package com.gerotac.auth.requirement.data.remote.api

import com.gerotac.auth.requirement.data.remote.assignrequirement.request.AssignRequest
import com.gerotac.auth.requirement.data.remote.getdetailrequirement.ResultDto
import com.gerotac.auth.requirement.data.remote.getrequirement.GetRequirementResponse
import com.gerotac.auth.requirement.data.remote.requirementsave.RequirementResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface RequirementApi {

    @Multipart
    @POST("requierements")
    suspend fun doRequirementApi(
        @Header("Authorization") token: String,
        @Part("area_intervention") area_intervention: Int,
        @Part("description") description: String,
        @Part("cause_problem") cause_problem: String,
        @Part("efect_problem") efect_problem: String,
        @Part("impact_problem") impact_problem: String,
        @Part document : MutableList<MultipartBody.Part>
    ): RequirementResponse

    @GET("requierements/")
    suspend fun doGetRequirementApi(
        @Header("Authorization") token: String,
        @Query("page") current_page: Int? = null,
        @Query("requierementId") id: String? = null
    ): GetRequirementResponse

    @GET("requierements/{requierementId}")
    suspend fun doGetDetailRequirementApi(
        @Header("Authorization") token: String,
        @Path("requierementId") id: Int
    ): ResultDto

    @GET("requierements-assign")
    suspend fun doAssignRequirementApi(
        @Header("Authorization") token: String,
        @Body user: AssignRequest,
    ): com.gerotac.auth.requirement.data.remote.getrequirement.Result

    @GET("interventions")
    suspend fun doInterventionApi(
        @Header("Authorization") token: String,
        @Body user: AssignRequest,
    ): com.gerotac.auth.requirement.data.remote.getrequirement.Result
}