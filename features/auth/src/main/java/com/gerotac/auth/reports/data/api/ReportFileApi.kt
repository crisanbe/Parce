package com.gerotac.auth.reports.data.api

import com.gerotac.auth.reports.data.remote.request.ReportRequest
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Streaming

interface ReportFileApi {
    @Streaming
    @POST("report-type-bussines")
    suspend fun doReportApi(
        @Header("Authorization") token: String,
        @Body date: ReportRequest
    ): ResponseBody

    @Streaming
    @POST("report-gender")
    suspend fun doReportGenderApi(
        @Header("Authorization") token: String,
        @Body date: ReportRequest
    ): ResponseBody

    @Streaming
    @POST("report-disability")
    suspend fun doReportDisabilityApi(
        @Header("Authorization") token: String,
        @Body date: ReportRequest
    ): ResponseBody

    @Streaming
    @POST("report-type-societies")
    suspend fun doReportSocietiesApi(
        @Header("Authorization") token: String,
        @Body date: ReportRequest
    ): ResponseBody

    @Streaming
    @POST("report-ethnic-group")
    suspend fun doReportEthnicGroupApi(
        @Header("Authorization") token: String,
        @Body date: ReportRequest
    ): ResponseBody

    @Streaming
    @POST("report-intervention-area")
    suspend fun doReportInterventionAreaApi(
        @Header("Authorization") token: String,
        @Body date: ReportRequest
    ): ResponseBody

    @Streaming
    @POST("report-type-intervention")
    suspend fun doReportTypeInterventionApi(
        @Header("Authorization") token: String,
        @Body date: ReportRequest
    ): ResponseBody

    @Streaming
    @POST("report-department")
    suspend fun doReportDepartmentApi(
        @Header("Authorization") token: String,
        @Body date: ReportRequest
    ): ResponseBody

    @Streaming
    @POST("report-city")
    suspend fun doReportCityApi(
        @Header("Authorization") token: String,
        @Body date: ReportRequest
    ): ResponseBody
}