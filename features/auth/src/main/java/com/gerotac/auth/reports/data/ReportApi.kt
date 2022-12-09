package com.gerotac.auth.reports.data

import com.gerotac.auth.reports.data.remote.request.ReportRequest
import com.gerotac.auth.reports.data.remote.response.ResponseReportDto
import com.gerotac.auth.requirement.data.remote.deleterequirement.ResponseDeleteRequirementDto
import com.gerotac.auth.requirement.data.remote.getdetailrequirement.DataDto
import com.gerotac.auth.requirement.data.remote.getrequirement.GetRequirementResponse
import com.gerotac.auth.requirement.data.remote.requirementsave.RequirementResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*
import java.io.File

interface ReportApi {
    @Streaming
    @POST("report-type-bussines")
    suspend fun doReportApi(
        @Header("Authorization") token: String,
        @Body date: ReportRequest
    ): ResponseBody

    fun ResponseBody.saveFile() {
        val destinationFile = File("")
        byteStream().use { inputStream->
            destinationFile.outputStream().use { outputStream->
                inputStream.copyTo(outputStream)
            }
        }
    }
}