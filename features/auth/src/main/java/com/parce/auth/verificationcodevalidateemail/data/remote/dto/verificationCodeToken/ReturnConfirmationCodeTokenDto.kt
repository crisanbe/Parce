package com.parce.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken

import com.google.gson.annotations.SerializedName
import com.parce.auth.codeverificationRegister.domain.model.ReturnCodeVerification
import com.parce.auth.verificationcodevalidateemail.domain.model.ReturnVerificationCodeValidateEmail

data class ReturnConfirmationCodeTokenDto(
    @SerializedName("message") val message: String,
    @SerializedName("state") val state: Boolean,
    @SerializedName("status") val status: String
)

fun ReturnConfirmationCodeTokenDto.toTokenCodeToken(): ReturnVerificationCodeValidateEmail {
    return ReturnVerificationCodeValidateEmail(
        message = message,
        state = state,
        status = status
    )
}
