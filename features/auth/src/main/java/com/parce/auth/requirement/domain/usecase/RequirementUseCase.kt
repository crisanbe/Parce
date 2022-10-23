package com.parce.auth.requirement.domain.usecase

import com.parce.auth.requirement.domain.model.RequirementReply
import com.parce.auth.requirement.domain.repository.RequirementRepository
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

class RequirementUseCase @Inject constructor(
    private val repository: RequirementRepository) {

        operator fun invoke(
            token: String,
            area_intervention: Int,
            description: String,
            cause_problem: String,
            efect_problem: String,
            impact_problem: String,
            file: MutableList<MultipartBody.Part> = mutableListOf()
        ): Flow<Resource<RequirementReply>> {
            return repository.doRequirement(
                token = token,
                area_intervention = area_intervention,
                description = description,
                cause_problem = cause_problem,
                efect_problem = efect_problem,
                impact_problem = impact_problem,
                file = file,
            )
        }
 /*   operator fun invoke(
        token: String,
        area_intervention: Int,
        description: String,
        cause_problem: String,
        efect_problem: String,
        impact_problem: String,
        file: MultipartBody.Part? = null
    ): Flow<Resource<RequirementResponse>> = flow {
        try {
            emit(Resource.Loading())
            val requestRequirement = file?.let {
                repository.doRequirement(
                    token = token,
                    area_intervention = area_intervention,
                    description = description,
                    cause_problem = cause_problem,
                    efect_problem = efect_problem,
                    impact_problem = impact_problem,
                    file = it,
                )
            }
            emit(Resource.Success(requestRequirement))
        } catch (e: Exception) {
            val message = when (e) {
                is HttpException -> {
                    Gson().fromJson(
                        e.response()?.errorBody()?.string(),
                        LoginState::class.java
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
    }*/
}
