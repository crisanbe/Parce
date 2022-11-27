package com.gerotac.auth.requirement.domain.usecase

import com.gerotac.auth.register.presentation.state.RegisterState
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
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RequirementUseCase @Inject constructor(
    private val repository: RequirementRepository
) {

    operator fun invoke(
        token: String,
        area_intervention: Int,
        description: RequestBody,
        cause_problem: RequestBody,
        efect_problem: RequestBody,
        impact_problem: RequestBody,
        file: MutableList<MultipartBody.Part> = mutableListOf()
    ): Flow<Resource<RequirementResponse>> = flow {
        try {
            emit(Resource.Loading())
            val requirement = repository.doRequirement(
                token = token,
                area_intervention = area_intervention,
                description = description,
                cause_problem = cause_problem,
                efect_problem = efect_problem,
                impact_problem = impact_problem,
                file = file,
            )
            emit(Resource.Success(requirement))
        } catch (e: Exception) {
            val message = when (e) {
                is HttpException -> {
                    Gson().fromJson(
                        e.response()?.errorBody()?.string(),
                        RequirementState::class.java
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

