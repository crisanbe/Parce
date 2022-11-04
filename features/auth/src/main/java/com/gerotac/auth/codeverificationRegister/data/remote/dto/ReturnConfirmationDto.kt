package com.gerotac.auth.codeverificationRegister.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.gerotac.auth.codeverificationRegister.domain.model.ReturnCodeVerification

data class ReturnConfirmationDto(
    @SerializedName("message") val message: String,
    @SerializedName("state") val state: Boolean,
    @SerializedName("status") val status: String
)

fun ReturnConfirmationDto.toTokenCode(): ReturnCodeVerification {
    return ReturnCodeVerification(
        message = message,
        state = state,
        status = status
    )
}
