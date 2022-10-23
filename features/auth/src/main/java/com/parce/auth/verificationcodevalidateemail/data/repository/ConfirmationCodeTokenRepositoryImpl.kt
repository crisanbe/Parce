package com.parce.auth.verificationcodevalidateemail.data.repository

import com.parce.auth.verificationcodevalidateemail.data.remote.VerificationCodeValidateEmailApi
import com.parce.auth.verificationcodevalidateemail.data.remote.dto.gettoken.VerificationCodeValidateEmailDto
import com.parce.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.CodeVerificationTokenDto
import com.parce.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.ReturnConfirmationCodeTokenDto
import com.parce.auth.verificationcodevalidateemail.domain.model.ReturnVerificationCodeValidateEmail
import com.parce.auth.verificationcodevalidateemail.domain.repository.VerificationCodeTokenRepository
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
