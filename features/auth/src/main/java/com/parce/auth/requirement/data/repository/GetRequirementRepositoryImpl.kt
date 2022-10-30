package com.parce.auth.requirement.data.repository

import com.google.gson.Gson
import com.parce.auth.requirement.data.remote.api.RequirementApi
import com.parce.auth.requirement.data.remote.getrequirement.toGetPagination
import com.parce.auth.requirement.data.remote.getrequirement.toGetRequirement
import com.parce.auth.requirement.domain.model.getrequirement.GetRequirement
import com.parce.auth.requirement.domain.model.getrequirement.Pagination
import com.parce.auth.requirement.domain.repository.GetRequirementRepository
import com.parce.auth.requirement.presentation.state.GetPageState
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRequirementRepositoryImpl @Inject constructor(private val api: RequirementApi) :
    GetRequirementRepository {
    override fun doGetRequirement(
        token: String,
        current_page : Int,
        id : String?
    ): Flow<Resource<GetRequirement>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.doGetRequirementApi(
                token = token,id = id, current_page =current_page).toGetRequirement()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong",
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

    override fun doGetPagination(token: String): Flow<Resource<Pagination>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.doGetRequirementApi(
                token = token).toGetPagination()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            val message = when (e) {
                is HttpException -> {
                    Gson().fromJson(
                        e.response()?.errorBody()?.string(),
                        GetPageState::class.java
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
