package com.gerotac.auth.reports.data.repository

import com.gerotac.auth.reports.data.ReportApi
import com.gerotac.auth.reports.data.remote.request.ReportRequest
import com.gerotac.auth.reports.data.remote.response.ResponseReportDto
import com.gerotac.auth.reports.domain.model.ResponseReport
import com.gerotac.auth.reports.domain.repository.ReportRepository
import com.gerotac.auth.requirement.data.remote.api.RequirementApi
import com.gerotac.auth.requirement.data.remote.requirementsave.RequirementResponse
import com.gerotac.auth.requirement.domain.repository.RequirementRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val api: ReportApi,
) :
    ReportRepository {
    override suspend fun doReport(
        token: String,
        date: ReportRequest,
    ): ResponseBody {
        return api.doReportApi(
            token = token,
            date = date
        )
    }
}
