package com.gerotac.auth.intervention.createintervention.data.remote.api

import com.gerotac.auth.intervention.createintervention.data.remote.response.ResponseSaveInterventionDto
import com.gerotac.auth.requirement.data.remote.getdetailrequirement.DataDto
import com.gerotac.auth.requirement.data.remote.getrequirement.GetRequirementResponse
import com.gerotac.auth.requirement.data.remote.requirementsave.RequirementResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface SaveInterventionApi {

    @Multipart
    @POST("interventions")
    suspend fun doSaveInterventionApi(
        @Header("Authorization") token: String,
        @Part("type_intervention") type_intervention: RequestBody,
        @Part("description") description: RequestBody,
        @Part("requierement_id") requierement_id: Int,
        @Part document: MutableList<MultipartBody.Part>
    ): ResponseSaveInterventionDto
}