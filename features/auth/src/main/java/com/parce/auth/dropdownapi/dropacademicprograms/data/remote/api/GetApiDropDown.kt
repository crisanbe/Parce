package com.parce.auth.dropdownapi.dropacademicprograms.data.remote.api

import com.parce.auth.dropdownapi.dropacademicprograms.data.remote.response.departament.ResponseLocation
import com.parce.auth.dropdownapi.dropacademicprograms.data.remote.response.responsedrop.ResponseAcademicPrograms
import retrofit2.http.GET
import retrofit2.http.Header

interface GetApiDropDown {
    @GET("academicprograms")
    suspend fun doAcademicPrograms(
        @Header("Authorization") token: String
    ): ResponseAcademicPrograms

    @GET("typesocieties")
    suspend fun doTypeCompany(
        @Header("Authorization") token: String
    ): ResponseAcademicPrograms

    @GET("locations/departments")
    suspend fun doLocation(
        @Header("Authorization") token: String
    ): ResponseLocation
}
