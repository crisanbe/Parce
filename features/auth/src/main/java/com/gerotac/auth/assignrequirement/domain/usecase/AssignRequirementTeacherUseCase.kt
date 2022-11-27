package com.gerotac.auth.assignrequirement.domain.usecase

import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.request.AssignRequest
import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.response.AssignDto
import com.gerotac.auth.assignrequirement.domain.repository.AssignRepository
import com.gerotac.auth.assignrequirement.presentation.state.AssignState
import com.gerotac.shared.network.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AssignRequirementTeacherUseCase @Inject constructor(
    private val repository: AssignRepository
) {
    operator fun invoke(
        token: String,
        teacher: AssignRequest
    ): Flow<Resource<AssignDto>> = flow {
        try {
            emit(Resource.Loading())
            val tokenRequest = repository.doAssignTeacher(token, teacher)
            emit(Resource.Success(tokenRequest))
        } catch (e: Exception) {
            val message = when (e) {
                is HttpException -> {
                    Gson().fromJson(
                        e.response()?.errorBody()?.string(),
                        AssignState::class.java
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
