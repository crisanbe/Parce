package com.gerotac.auth.verificationcodevalidateemail.data.remote.dto.gettoken

import com.google.gson.annotations.SerializedName
import com.gerotac.auth.verificationcodevalidateemail.domain.model.ReturnVerificationCodeValidateEmail

data class VerificationCodeValidateEmailDto(
    @SerializedName("message") val message: String,
    @SerializedName("state") val state: Boolean,
    @SerializedName("status") val status: String
)

fun VerificationCodeValidateEmailDto.toTokenCodeValidateEmail(): ReturnVerificationCodeValidateEmail {
    return ReturnVerificationCodeValidateEmail(
        message = message,
        state = state,
        status = status
    )
}
