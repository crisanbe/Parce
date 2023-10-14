package com.gerotac.auth.verificationcodevalidateemail.data.repository

import com.gerotac.auth.verificationcodevalidateemail.data.remote.VerificationCodeValidateEmailApi
import com.gerotac.auth.verificationcodevalidateemail.data.remote.dto.gettoken.VerificationCodeValidateEmailDto
import com.gerotac.auth.verificationcodevalidateemail.domain.repository.VerificationCodeValidateEmailRepository
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
