package com.parce.auth.verificationcodevalidateemail.domain.repository

import com.parce.auth.verificationcodevalidateemail.data.remote.dto.gettoken.VerificationCodeValidateEmailDto

interface VerificationCodeValidateEmailRepository {
    suspend fun doVerificationCodeValidateEmail(
        token: String
    ): VerificationCodeValidateEmailDto
}
