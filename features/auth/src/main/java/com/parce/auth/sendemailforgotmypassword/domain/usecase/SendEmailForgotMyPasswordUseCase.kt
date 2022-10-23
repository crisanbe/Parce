package com.parce.auth.sendemailforgotmypassword.domain.usecase

import com.parce.auth.sendemailforgotmypassword.data.remote.dto.SendEmailForgotMyPasswordDto
import com.parce.auth.sendemailforgotmypassword.data.remote.dto.RetuntResendNewCodeDto
import com.parce.auth.sendemailforgotmypassword.domain.repository.SendEmailForgotMyPasswordRepository
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SendEmailForgotMyPasswordUseCase @Inject constructor(private val repository: SendEmailForgotMyPasswordRepository) {
    operator fun invoke(email: SendEmailForgotMyPasswordDto): Flow<Resource<RetuntResendNewCodeDto>> = flow {
        try {
            emit(Resource.Loading<RetuntResendNewCodeDto>())
            val email = repository.doResendNewCode(email = email)
            emit(Resource.Success<RetuntResendNewCodeDto>(email))
        } catch (e: HttpException) {
            emit(
                Resource.Error<RetuntResendNewCodeDto>(
                    e.localizedMessage ?: "An unexpected error ocurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<RetuntResendNewCodeDto>("Couldn't reach server. Check you internet connection"))
        }
    }
}
