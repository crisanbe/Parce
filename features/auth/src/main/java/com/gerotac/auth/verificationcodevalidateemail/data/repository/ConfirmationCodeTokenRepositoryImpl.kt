package com.gerotac.auth.verificationcodevalidateemail.data.repository

import com.gerotac.auth.verificationcodevalidateemail.data.remote.VerificationCodeValidateEmailApi
import com.gerotac.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.CodeVerificationTokenDto
import com.gerotac.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.ReturnConfirmationCodeTokenDto
import com.gerotac.auth.verificationcodevalidateemail.domain.repository.VerificationCodeTokenRepository
import javax.inject.Inject

class ConfirmationCodeTokenRepositoryImpl @Inject constructor(
    private val api: VerificationCodeValidateEmailApi) :
    VerificationCodeTokenRepository {
    override suspend fun doConfirmationCodeToken(
        token: String,
        code: CodeVerificationTokenDto
    ): ReturnConfirmationCodeTokenDto {
        return api.doConfirmationCodeToken(token = token, code)
    }
}
