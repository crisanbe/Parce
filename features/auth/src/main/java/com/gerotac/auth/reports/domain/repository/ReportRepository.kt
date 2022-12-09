package com.gerotac.auth.reports.domain.repository

import com.gerotac.auth.reports.data.remote.request.ReportRequest
import com.gerotac.auth.reports.data.remote.response.ResponseReportDto
import com.gerotac.auth.reports.domain.model.ResponseReport
import com.gerotac.auth.requirement.data.remote.requirementsave.RequirementResponse
import com.gerotac.auth.requirement.domain.model.requirement.RequirementReply
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody

interface ReportRepository {
   suspend fun doReport(
        token: String,
        date: ReportRequest
    ): ResponseBody
}