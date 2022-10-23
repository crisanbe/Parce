package com.parce.auth.verificationcodevalidateemail.data.repository

import com.parce.auth.verificationcodevalidateemail.data.remote.VerificationCodeValidateEmailApi
import com.parce.auth.verificationcodevalidateemail.data.remote.dto.gettoken.VerificationCodeValidateEmailDto
import com.parce.auth.verificationcodevalidateemail.domain.repository.VerificationCodeValidateEmailRepository
import javax.inject.Inject

class VerificationCodeValidateEmailRepositoryImpl @Inject constructor(
    private val api: VerificationCodeValidateEmailApi) :
    VerificationCodeValidateEmailRepository {
    override suspend fun doVerificationCodeValidateEmail(
        token: String
    ): VerificationCodeValidateEmailDto {
        return api.doVerificationCodeValidateEmail(token = token)
    }
}
