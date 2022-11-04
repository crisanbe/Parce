package com.gerotac.auth.codeverificationRegister.domain.usecase

import com.gerotac.auth.codeverificationRegister.data.remote.dto.ParameterCodeConfirmationDto
import com.gerotac.auth.codeverificationRegister.data.remote.dto.ReturnConfirmationDto
import com.gerotac.auth.codeverificationRegister.domain.repository.VerificationCodeRepository
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ConfirmationCodeUseCase @Inject constructor(private val repository: VerificationCodeRepository) {
    operator fun invoke(token: String, code: ParameterCodeConfirmationDto
    ): Flow<Resource<ReturnConfirmationDto>> = flow {
        try {
            emit(Resource.Loading<ReturnConfirmationDto>())
            val verificationCode = repository.doConfirmationCode(token = token, code = code)
            emit(Resource.Success<ReturnConfirmationDto>(verificationCode))
        } catch (e: HttpException) {
            emit(
                Resource.Error<ReturnConfirmationDto>(
                    e.localizedMessage ?: "An unexpected error ocurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<ReturnConfirmationDto>("Couldn't reach server. Check you internet connection"))
        }
    }
}
