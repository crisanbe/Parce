package com.parce.auth.codeverificationRegister.domain.repository

import com.parce.auth.codeverificationRegister.data.remote.dto.ParameterCodeConfirmationDto
import com.parce.auth.codeverificationRegister.data.remote.dto.ReturnConfirmationDto

interface VerificationCodeRepository {
    suspend fun doConfirmationCode(
        token: String,
        code: ParameterCodeConfirmationDto
    ): ReturnConfirmationDto
}
