package com.parce.auth.sendemailforgotmypassword.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.parce.auth.sendemailforgotmypassword.domain.model.ReturnSendEmailForgotMyPassword

data class RetuntResendNewCodeDto(
    @SerializedName("message") val message: String,
    @SerializedName("state") val state: Boolean,
    @SerializedName("status") val status: String
)

fun RetuntResendNewCodeDto.toTokenCode(): ReturnSendEmailForgotMyPassword {
    return ReturnSendEmailForgotMyPassword(
        message = message,
        state = state,
        status = status
    )
}
