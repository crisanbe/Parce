package com.parce.auth.requirement.data.repository

import com.google.gson.Gson
import com.parce.auth.requirement.data.remote.api.RequirementApi
import com.parce.auth.requirement.data.remote.getdetailrequirement.GetDetailResponse
import com.parce.auth.requirement.domain.repository.DetailRequirementRepository
import com.parce.auth.requirement.presentation.state.RequirementState
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDetailRequirementRepositoryImpl @Inject constructor(
    private val api: RequirementApi,
) :
    DetailRequirementRepository {
    override fun doDetailRequirement(
        token: String,
        id: Int
    ): Flow<Resource<GetDetailResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.doGetDetailRequirementApi(token = token, id = id)
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
