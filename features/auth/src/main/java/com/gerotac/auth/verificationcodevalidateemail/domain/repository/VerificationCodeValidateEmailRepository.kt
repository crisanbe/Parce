package com.gerotac.auth.verificationcodevalidateemail.domain.repository

import com.gerotac.auth.verificationcodevalidateemail.data.remote.dto.gettoken.VerificationCodeValidateEmailDto

interface VerificationCodeValidateEmailRepository {
    suspend fun doVerificationCodeValidateEmail(
        token: String
    ): VerificationCodeValidateEmailDto
}
