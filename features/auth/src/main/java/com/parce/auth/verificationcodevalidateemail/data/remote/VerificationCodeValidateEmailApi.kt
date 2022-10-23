package com.parce.auth.verificationcodevalidateemail.data.remote

import com.parce.auth.verificationcodevalidateemail.data.remote.dto.gettoken.VerificationCodeValidateEmailDto
import com.parce.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.CodeVerificationTokenDto
import com.parce.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken.ReturnConfirmationCodeTokenDto
import com.parce.auth.verificationcodevalidateemail.domain.model.ReturnVerificationCodeValidateEmail
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface VerificationCodeValidateEmailApi {
    @GET("email/new-code-verify")
    suspend fun doVerificationCodeValidateEmail(
        @Header("Authorization") token: String
    ): VerificationCodeValidateEmailDto

    @POST("code/verify")
    suspend fun doConfirmationCodeToken(
        @Header("Authorization") token: String,
        @Body confirmationCodeToken: CodeVerificationTokenDto
    ): ReturnConfirmationCodeTokenDto

}
