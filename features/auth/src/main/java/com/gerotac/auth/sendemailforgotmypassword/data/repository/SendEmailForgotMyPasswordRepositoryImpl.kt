package com.gerotac.auth.sendemailforgotmypassword.data.repository

import com.gerotac.auth.sendemailforgotmypassword.data.remote.SendEmailForgotMyPasswordApi
import com.gerotac.auth.sendemailforgotmypassword.data.remote.dto.SendEmailForgotMyPasswordDto
import com.gerotac.auth.sendemailforgotmypassword.data.remote.dto.RetuntResendNewCodeDto
import com.gerotac.auth.sendemailforgotmypassword.domain.repository.SendEmailForgotMyPasswordRepository
import javax.inject.Inject

class SendEmailForgotMyPasswordRepositoryImpl @Inject constructor(private val api: SendEmailForgotMyPasswordApi) :
    SendEmailForgotMyPasswordRepository {
    override suspend fun doResendNewCode(email: SendEmailForgotMyPasswordDto): RetuntResendNewCodeDto {
        return api.doReturnNewCode(email)
    }
}
