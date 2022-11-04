package com.gerotac.auth.codeverificationRegister.domain.repository

import com.gerotac.auth.codeverificationRegister.data.remote.dto.ParameterCodeConfirmationDto
import com.gerotac.auth.codeverificationRegister.data.remote.dto.ReturnConfirmationDto

interface VerificationCodeRepository {
    suspend fun doConfirmationCode(
        token: String,
        code: ParameterCodeConfirmationDto
    ): ReturnConfirmationDto
}
