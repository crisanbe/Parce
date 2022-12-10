package com.gerotac.auth.reports.data.api

import com.gerotac.auth.reports.data.remote.request.ReportRequest
import com.gerotac.auth.reports.data.remote.response.ResponseReportDto
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Streaming

interface ReportResponseApi {
    @POST("report-type-bussines")
    suspend fun doReportResponseApi(
        @Header("Authorization") token: String,
        @Body date: ReportRequest
    ): ResponseReportDto

    @POST("report-type-bussines")
    suspend fun doReportGenderResponseApi(
        @Header("Authorization") token: String,
        @Body date: ReportRequest
    ): ResponseReportDto
}