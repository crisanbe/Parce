package com.gerotac.auth.approveanddisapprove.domain.usecase

import com.gerotac.auth.approveanddisapprove.data.remote.dto.response.ResponseApprove
import com.gerotac.auth.approveanddisapprove.domain.repository.ApproveInterventionRepository
import com.gerotac.auth.approveanddisapprove.presentation.state.ApproveAndDisapproveInterventionState
import com.gerotac.shared.network.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class DisapproveInterventionCase @Inject constructor(private val repository: ApproveInterventionRepository) {
    operator fun invoke(
        token: String,
        id: Int
    ): Flow<Resource<ResponseApprove>> = flow {
        try {
            emit(Resource.Loading())
            val updateRequirement =
                repository.doDisapproveIntervention(token = token,id)
            emit(Resource.Success(updateRequirement))
        } catch (e: Exception) {
            val message = when (e) {
                is HttpException -> {
                    Gson().fromJson(
                        e.response()?.errorBody()?.string(),
                        ApproveAndDisapproveInterventionState::class.java
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
