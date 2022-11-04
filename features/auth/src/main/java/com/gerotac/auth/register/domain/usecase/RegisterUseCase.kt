package com.gerotac.auth.register.domain.usecase

import com.google.gson.Gson
import com.gerotac.auth.register.data.remote.dto.RegisterDto
import com.gerotac.auth.register.data.remote.dto.TokenRegisterDto
import com.gerotac.auth.register.domain.repository.RegisterRepository
import com.gerotac.auth.register.presentation.state.RegisterState
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: RegisterRepository) {
    operator fun invoke(register: RegisterDto): Flow<Resource<TokenRegisterDto>> = flow {
        try {
            emit(Resource.Loading())
            val token = repository.doRegister(register)
            /*repository.insertTokenRegister(Authorization(null,token.authorization.token,""))*/
            emit(Resource.Success(token))
        } catch (e: Exception) {
            val message = when (e) {
                is HttpException -> {
                    Gson().fromJson(
                        e.response()?.errorBody()?.string(),
                        RegisterState::class.java
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
