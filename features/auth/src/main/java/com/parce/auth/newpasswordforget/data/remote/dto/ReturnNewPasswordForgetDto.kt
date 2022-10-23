package com.parce.auth.newpasswordforget.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.parce.auth.newpasswordforget.domain.model.ReturnNewPasswordForget

data class ReturnNewPasswordForgetDto(
    @SerializedName("state") val state: String,
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String
)

fun ReturnNewPasswordForgetDto.toNewPasswordForget(): ReturnNewPasswordForget {
    return ReturnNewPasswordForget(
        message = message,
        status = status,
        state = state
    )
}
