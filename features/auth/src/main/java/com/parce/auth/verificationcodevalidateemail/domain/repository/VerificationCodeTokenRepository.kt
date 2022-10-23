package com.parce.auth.verificationcodevalidateemail.domain.repository

import com.parce.auth.verificationcodevalidateemail.data.remote.dto.gettoken.VerificationCodeValidateEmailDto
import com.parce.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.CodeVerificationTokenDto
import com.parce.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.ReturnConfirmationCodeTokenDto
import com.parce.auth.verificationcodevalidateemail.domain.model.ReturnVerificationCodeValidateEmail

interface VerificationCodeTokenRepository {
    suspend fun doConfirmationCodeToken(
        token: String,
        code: CodeVerificationTokenDto
    ): ReturnConfirmationCodeTokenDto
}
