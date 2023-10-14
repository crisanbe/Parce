package com.gerotac.auth.requirement.data.repository

import com.google.gson.Gson
import com.gerotac.auth.requirement.data.remote.api.RequirementApi
import com.gerotac.auth.requirement.data.remote.getrequirement.toGetPagination
import com.gerotac.auth.requirement.data.remote.getrequirement.toGetRequirements
import com.gerotac.auth.requirement.domain.model.getrequirement.Pagination
import com.gerotac.auth.requirement.domain.model.getrequirement.Result
import com.gerotac.auth.requirement.domain.repository.GetRequirementRepository
import com.gerotac.auth.requirement.presentation.state.GetPageState
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRequirementRepositoryImpl @Inject constructor(
    private val api: RequirementApi
) :
    GetRequirementRepository {

    override fun doGetRequirement(
        token: String,
        current_page : Int
    ): Flow<Resource<List<Result>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.doGetRequirementApi(
                token = token, current_page =current_page).toGetRequirements()
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
