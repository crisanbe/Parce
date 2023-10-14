package com.gerotac.auth.codeverificationRegister.data.repository

import com.gerotac.auth.codeverificationRegister.data.remote.ConfirmationCodeApi
import com.gerotac.auth.codeverificationRegister.data.remote.dto.ParameterCodeConfirmationDto
import com.gerotac.auth.codeverificationRegister.data.remote.dto.ReturnConfirmationDto
import com.gerotac.auth.codeverificationRegister.domain.repository.VerificationCodeRepository
import javax.inject.Inject

class ConfirmationCodeRepositoryImpl @Inject constructor(private val api: ConfirmationCodeApi) :
    VerificationCodeRepository {
    override suspend fun doConfirmationCode(
        token: String,
        code: ParameterCodeConfirmationDto
    ): ReturnConfirmationDto {
        return api.doConfirmationCode(token = token, code)
    }
}
