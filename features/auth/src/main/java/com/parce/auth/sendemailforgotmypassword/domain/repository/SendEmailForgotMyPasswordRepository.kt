package com.parce.auth.sendemailforgotmypassword.domain.repository

import com.parce.auth.sendemailforgotmypassword.data.remote.dto.SendEmailForgotMyPasswordDto
import com.parce.auth.sendemailforgotmypassword.data.remote.dto.RetuntResendNewCodeDto

interface SendEmailForgotMyPasswordRepository {
    suspend fun doResendNewCode(email: SendEmailForgotMyPasswordDto): RetuntResendNewCodeDto
}