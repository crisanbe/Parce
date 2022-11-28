package com.gerotac.auth.approveanddisapprove.data.remote.api

import com.gerotac.auth.approveanddisapprove.data.remote.dto.response.ResponseApprove
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApproveInterventionApi {
    @GET("interventions-approve/{id}")
    suspend fun doApproveIntervention(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): ResponseApprove
}