package com.gerotac.auth.sendemailforgotmypassword.domain.repository

import com.gerotac.auth.sendemailforgotmypassword.data.remote.dto.SendEmailForgotMyPasswordDto
import com.gerotac.auth.sendemailforgotmypassword.data.remote.dto.RetuntResendNewCodeDto

interface SendEmailForgotMyPasswordRepository {
    suspend fun doResendNewCode(email: SendEmailForgotMyPasswordDto): RetuntResendNewCodeDto
}