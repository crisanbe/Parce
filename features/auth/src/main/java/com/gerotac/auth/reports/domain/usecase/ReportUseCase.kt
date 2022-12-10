package com.gerotac.auth.reports.domain.usecase

import com.gerotac.auth.reports.data.remote.request.ReportRequest
import com.gerotac.auth.reports.data.remote.response.ResponseReportDto
import com.gerotac.auth.reports.domain.repository.ReportMessageRepository
import com.gerotac.auth.reports.presentation.statereports.ReportState
import com.gerotac.shared.network.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReportUseCase @Inject constructor(
    private val repository: ReportMessageRepository
) {

    operator fun invoke(
        token: String,
        date: ReportRequest
    ): Flow<Resource<ResponseReportDto>> = flow {
        try {
            emit(Resource.Loading())
            val report = repository.doReportResponse(
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

