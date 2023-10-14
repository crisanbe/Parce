package com.gerotac.auth.verificationcodevalidateemail.domain.usecase

import com.gerotac.auth.verificationcodevalidateemail.data.remote.dto.gettoken.VerificationCodeValidateEmailDto
import com.gerotac.auth.verificationcodevalidateemail.domain.repository.VerificationCodeValidateEmailRepository
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class VerificationCodeValidateEmailUseCase @Inject constructor(
    private val repository:
    VerificationCodeValidateEmailRepository
) {
    operator fun invoke(token: String): Flow<Resource<VerificationCodeValidateEmailDto>> = flow {
        try {
            emit(Resource.Loading<VerificationCodeValidateEmailDto>())
            val verificationCode = repository.doVerificationCodeValidateEmail(token = token)
            emit(Resource.Success<VerificationCodeValidateEmailDto>(verificationCode))
        } catch (e: HttpException) {
            emit(
                Resource.Error<VerificationCodeValidateEmailDto>(
                    e.localizedMessage ?: "An unexpected error ocurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<VerificationCodeValidateEmailDto>("Couldn't reach server. Check you internet connection"))
        }
    }
}
