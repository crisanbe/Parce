package com.parce.auth.requirement.data.remote.api

import com.parce.auth.requirement.data.remote.getrequirement.GetRequirementResponse
import com.parce.auth.requirement.data.remote.requirement.RequirementResponse
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
        @Query("page") current_page: Int? = null
    ): GetRequirementResponse

}