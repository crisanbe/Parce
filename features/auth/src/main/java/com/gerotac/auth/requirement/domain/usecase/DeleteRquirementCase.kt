package com.gerotac.auth.requirement.domain.usecase

import com.gerotac.auth.requirement.data.remote.deleterequirement.ResponseDeleteRequirementDto
import com.gerotac.auth.requirement.domain.repository.DeleteRequirementRepository
import com.gerotac.auth.requirement.presentation.state.DeleteRequirementState
import com.gerotac.auth.updaterequirement.data.remote.dto.request.ResquestUpdateRequirement
import com.gerotac.auth.updaterequirement.data.remote.dto.response.ResponseUpdateRequirementDto
import com.gerotac.auth.updaterequirement.domain.repository.UpdateRequirementRepository
import com.gerotac.auth.updaterequirement.presentation.state.UpdateRequirementState
import com.gerotac.shared.network.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class DeleteRequirementCase @Inject constructor(private val repository: DeleteRequirementRepository) {
    operator fun invoke(
        token: String,
        id: Int
    ): Flow<Resource<ResponseDeleteRequirementDto>> = flow {
        try {
            emit(Resource.Loading())
            val updateRequirement =
                repository.doDeleteRequirement(token = token,id)
            emit(Resource.Success(updateRequirement))
        } catch (e: Exception) {
            val message = when (e) {
                is HttpException -> {
                    Gson().fromJson(
                        e.response()?.errorBody()?.string(),
                        DeleteRequirementState::class.java
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
