package com.gerotac.auth.dropdownapi.dropdown.data.remote.api

import com.gerotac.auth.dropdownapi.dropdown.data.remote.response.areainterventions.GetAreasInterventionsDto
import com.gerotac.auth.dropdownapi.dropdown.data.remote.response.departament.ResponseLocation
import com.gerotac.auth.dropdownapi.dropdown.data.remote.response.listateacher.ListTeacherDto
import com.gerotac.auth.dropdownapi.dropdown.data.remote.response.responsedrop.ResponseAcademicPrograms
import com.gerotac.auth.dropdownapi.dropdown.data.remote.response.studentbyarea.DataStudentByAreaDto
import com.gerotac.auth.dropdownapi.dropdown.data.remote.response.studentbyarea.GetStudentByArea
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("docents")
    suspend fun doTeacher(
        @Header("Authorization") token: String
    ): ListTeacherDto

    @GET("areainterventions")
    suspend fun doAreaInterventions(
        @Header("Authorization") token: String
    ): GetAreasInterventionsDto

    @GET("students?")
    suspend fun doGetStudentByArea(
        @Header("Authorization") token: String,
        @Query("requierementId") requierementId: Int
    ): GetStudentByArea
}
