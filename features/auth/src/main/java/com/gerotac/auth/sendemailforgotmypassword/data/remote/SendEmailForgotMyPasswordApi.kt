package com.gerotac.auth.sendemailforgotmypassword.data.remote

import com.gerotac.auth.sendemailforgotmypassword.data.remote.dto.SendEmailForgotMyPasswordDto
import com.gerotac.auth.sendemailforgotmypassword.data.remote.dto.RetuntResendNewCodeDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SendEmailForgotMyPasswordApi {
    @POST("auth/password/email")
    suspend fun doReturnNewCode(
        @Body resendNewCode: SendEmailForgotMyPasswordDto
    ): RetuntResendNewCodeDto
}
