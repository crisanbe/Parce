package com.parce.auth.requirement.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.parce.auth.requirement.data.remote.api.RequirementApi
import com.parce.auth.requirement.data.remote.requirement.toRequirementResponse
import com.parce.auth.requirement.domain.model.requirement.RequirementReply
import com.parce.auth.requirement.domain.repository.RequirementRepository
import com.parce.auth.requirement.presentation.state.RequirementState
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RequirementRepositoryImpl @Inject constructor(
    private val api: RequirementApi,
) :
    RequirementRepository {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun doRequirement(
        token: String,
        area_intervention: Int,
        description: String,
        cause_problem: String,
        efect_problem: String,
        impact_problem: String,
        file: MutableList<MultipartBody.Part>
    ): Flow<Resource<RequirementReply>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.doRequirementApi(
                token = token,
                area_intervention = area_intervention,
                description = description,
                cause_problem = cause_problem,
                efect_problem = efect_problem,
                impact_problem = impact_problem,
                document =  file
            ).toRequirementResponse()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            val message = when (e) {
                is HttpException -> {
                    Gson().fromJson(
                        e.response()?.errorBody()?.string(),
                        RequirementState::class.java
                    ).message
                }
                else -> e.message ?: "Oops, something went wrong"
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
/* val requestBody =  MultipartBody.Builder().setType(MultipartBody.FORM).apply {
              file.forEachIndexed { index, bytes ->
                  bytes?.let {
                      addFormDataPart("document[]", "$index.pdf",
                          it.asRequestBody("application/pdf".toMediaTypeOrNull()))
                  }
              }
          }.build()*/

/*    RequirementRepository {
    override suspend fun doRequirement(
        token: String,
        area_intervention: Int,
        description: String,
        cause_problem: String,
        efect_problem: String,
        impact_problem: String,
        file: MultipartBody.Part
    ): RequirementResponse {
        return api.doRequirementApi(
            token = token,
            area_intervention = area_intervention,
            description = description,
            cause_problem = cause_problem,
            efect_problem = efect_problem,
            impact_problem = impact_problem,
            document = file
        )
    }
}*/

/*
 class RequirementRepositoryImpl {

    suspend fun requeriment(
        token: String,
        area_intervention: Int,
        description: String,
        cause_problem: String,
        efect_problem: String,
        impact_problem: String,
        file: File
    ): Boolean {
        return try {
            RequirementApi.instance.doRequirementApi(
                token = token,
                area_intervention = area_intervention,
                description = description,
                cause_problem = cause_problem,
                efect_problem = efect_problem,
                impact_problem = impact_problem,
                document = MultipartBody.Part
                    .createFormData(
                        "document",
                        file.name,
                        file.asRequestBody()
                    )
            )
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        } catch (e: HttpException) {
            e.printStackTrace()
            false
        }
    }
}*/
