package com.gerotac.auth.login.domain.usecase

import com.google.gson.Gson
import com.gerotac.auth.login.data.remote.logindto.LoginDto
import com.gerotac.auth.login.data.remote.returnlogindto.ReturnLoginDto
import com.gerotac.auth.login.domain.repository.LoginRepository
import com.gerotac.auth.login.presentation.viewmodel.LoginState
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {
    operator fun invoke(login: LoginDto): Flow<Resource<ReturnLoginDto>> = flow {
        try {
            emit(Resource.Loading())
            val tokenRequest = repository.doLogin(login)
            emit(Resource.Success(tokenRequest))
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
    }
}
