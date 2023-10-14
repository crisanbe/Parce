package com.gerotac.auth.verificationcodevalidateemail.domain.repository

import com.gerotac.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.CodeVerificationTokenDto
import com.gerotac.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.ReturnConfirmationCodeTokenDto

interface VerificationCodeTokenRepository {
    suspend fun doConfirmationCodeToken(
        token: String,
        code: CodeVerificationTokenDto
    ): ReturnConfirmationCodeTokenDto
}
