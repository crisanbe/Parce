package com.parce.auth.updateuser.domain.usecase

import com.google.gson.Gson
import com.parce.auth.updateuser.data.remote.dto.ParameterUpdateUserDto
import com.parce.auth.updateuser.data.remote.dto.ReturnUpdateUserDto
import com.parce.auth.updateuser.domain.repository.UpdateUserRepository
import com.parce.auth.updateuser.presentation.viewmodel.UpdateUserState
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject



class UpdateUserCase @Inject constructor(private val repository: UpdateUserRepository) {
    operator fun invoke(
        token: String, parameterUpdateUser: ParameterUpdateUserDto
    ): Flow<Resource<ReturnUpdateUserDto>> = flow {
        try {
            emit(Resource.Loading())
            val updateUser =
                repository.doUpdateUser(token = token, parameterUpdateUser = parameterUpdateUser)
            emit(Resource.Success(updateUser))
        } catch (e: Exception) {
            val message = when (e) {
                is HttpException -> {
                    Gson().fromJson(
                        e.response()?.errorBody()?.string(),
                        UpdateUserState::class.java
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
