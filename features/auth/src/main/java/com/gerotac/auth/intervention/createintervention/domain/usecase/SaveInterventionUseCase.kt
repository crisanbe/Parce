package com.gerotac.auth.intervention.createintervention.domain.usecase

import com.gerotac.auth.intervention.createintervention.data.remote.response.ResponseSaveInterventionDto
import com.gerotac.auth.intervention.createintervention.domain.repository.SaveInterventionRepository
import com.gerotac.auth.intervention.createintervention.presentation.stateSaveIntervention.SaveInterventionState
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

class SaveInterventionUseCase @Inject constructor(
    private val repository: SaveInterventionRepository
) {

    operator fun invoke(
        token: String,
        type_intervention: RequestBody,
        description: RequestBody,
        requierement_id: Int,
        file: MutableList<MultipartBody.Part> = mutableListOf()
    ): Flow<Resource<ResponseSaveInterventionDto>> = flow {
        try {
            emit(Resource.Loading())
            val intervention = repository.doSaveIntervention(
                token = token,
                type_intervention = type_intervention,
                description = description,
                requierement_id = requierement_id,
                file = file,
            )
            emit(Resource.Success(intervention))
        } catch (e: Exception) {
            val message = when (e) {
                is HttpException -> {
                    Gson().fromJson(
                        e.response()?.errorBody()?.string(),
                        SaveInterventionState::class.java
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

