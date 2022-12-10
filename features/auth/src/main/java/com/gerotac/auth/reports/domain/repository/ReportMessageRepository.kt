package com.gerotac.auth.reports.domain.repository

import com.gerotac.auth.reports.data.remote.request.ReportRequest
import com.gerotac.auth.reports.data.remote.response.ResponseReportDto

interface ReportMessageRepository {
   suspend fun doReportResponse(
        token: String,
        date: ReportRequest
    ): ResponseReportDto

    suspend fun doReportGenderResponse(
        token: String,
        date: ReportRequest
    ): ResponseReportDto
}