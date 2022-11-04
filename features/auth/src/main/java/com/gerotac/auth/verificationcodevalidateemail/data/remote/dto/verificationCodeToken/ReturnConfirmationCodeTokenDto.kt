package com.gerotac.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken

import com.google.gson.annotations.SerializedName
import com.gerotac.auth.verificationcodevalidateemail.domain.model.ReturnVerificationCodeValidateEmail

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
