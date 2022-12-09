package com.gerotac.auth.reports.domain.usecase

import com.gerotac.auth.register.presentation.state.RegisterState
import com.gerotac.auth.reports.data.remote.request.ReportRequest
import com.gerotac.auth.reports.data.remote.response.ResponseReportDto
import com.gerotac.auth.reports.data.saveFile
import com.gerotac.auth.reports.domain.repository.ReportRepository
import com.gerotac.auth.reports.presentation.statereports.ReportState
import com.gerotac.auth.requirement.data.remote.requirementsave.RequirementResponse
import com.gerotac.auth.requirement.domain.model.requirement.RequirementReply
import com.gerotac.auth.requirement.domain.repository.RequirementRepository
import com.gerotac.auth.requirement.presentation.state.RequirementState
import com.gerotac.shared.network.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReportUseCase @Inject constructor(
    private val repository: ReportRepository
) {

    operator fun invoke(
        token: String,
        date: ReportRequest
    ): Flow<Resource<ResponseBody>> = flow {
        try {
            emit(Resource.Loading())
            val report = repository.doReport(
                token = token,
                date = date,
            )
            emit(Resource.Success(report))
        } catch (e: Exception) {
            val message = when (e) {
                is HttpException -> {
                    Gson().fromJson(
                        e.response()?.errorBody()?.string(),
                        ReportState::class.java
                    ).message
                }
                else -> e.message ?: "error"
            }
            emit(
                Resource.Error(
                    message = message,
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection",
                    data = null
                )
            )
        }
    }
}

