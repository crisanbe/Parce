package com.parce.auth.verificationcodevalidateemail.domain.usecase

import com.parce.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.CodeVerificationTokenDto
import com.parce.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.ReturnConfirmationCodeTokenDto
import com.parce.auth.verificationcodevalidateemail.domain.repository.VerificationCodeTokenRepository
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ConfirmationCodeTokenUseCase @Inject constructor(
    private val repository: VerificationCodeTokenRepository) {
    operator fun invoke(token: String, code: CodeVerificationTokenDto
    ): Flow<Resource<ReturnConfirmationCodeTokenDto>> = flow {
        try {
            emit(Resource.Loading())
            val verificationCode = repository.doConfirmationCodeToken(token = token, code = code)
            emit(Resource.Success(verificationCode))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error ocurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check you internet connection"))
        }
    }
}
