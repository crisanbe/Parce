package com.gerotac.auth.intervention.detailintervention.data.remote.api

import com.gerotac.auth.intervention.detailintervention.data.remote.responsedetailintervention.DetailResponseInterventionDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GetDetailInterventionApi {

    @GET("interventions/{id}")
    suspend fun doGetDetailInterventionApi(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): DetailResponseInterventionDto
}