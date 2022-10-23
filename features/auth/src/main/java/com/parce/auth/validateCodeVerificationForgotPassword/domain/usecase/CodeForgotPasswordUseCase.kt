package com.parce.auth.validateCodeVerificationForgotPassword.domain.usecase

import com.parce.auth.validateCodeVerificationForgotPassword.data.remote.dto.ParameterCodeForgotPasswordDto
import com.parce.auth.validateCodeVerificationForgotPassword.data.remote.dto.ReturnCodeForgotPasswordDto
import com.parce.auth.validateCodeVerificationForgotPassword.domain.repository.CodeForgotPasswordRepository
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CodeForgotPasswordUseCase @Inject constructor(private val repository: CodeForgotPasswordRepository) {
    operator fun invoke(token: ParameterCodeForgotPasswordDto): Flow<Resource<ReturnCodeForgotPasswordDto>> =
        flow {
            try {
                emit(Resource.Loading<ReturnCodeForgotPasswordDto>())
                val codeForgotPassword = repository.doCodeForgotPassword(token = token)
                emit(Resource.Success<ReturnCodeForgotPasswordDto>(codeForgotPassword))
            } catch (e: HttpException) {
                emit(
                    Resource.Error<ReturnCodeForgotPasswordDto>(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error<ReturnCodeForgotPasswordDto>("Couldn't reach server. Check you internet connection"))
            }
        }
}
