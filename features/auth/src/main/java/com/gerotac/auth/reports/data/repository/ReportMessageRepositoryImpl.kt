package com.gerotac.auth.reports.data.repository

import com.gerotac.auth.reports.data.api.ReportResponseApi
import com.gerotac.auth.reports.data.remote.request.ReportRequest
import com.gerotac.auth.reports.data.remote.response.ResponseReportDto
import com.gerotac.auth.reports.domain.repository.ReportMessageRepository
import javax.inject.Inject

class ReportMessageRepositoryImpl @Inject constructor(
    private val api: ReportResponseApi,
) :
    ReportMessageRepository {
    override suspend fun doReportResponse(
        token: String,
        date: ReportRequest,
    ): ResponseReportDto {
        return api.doReportResponseApi(
            token = token,
            date = date
        )
    }
}
