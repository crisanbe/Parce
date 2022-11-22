package com.gerotac.auth.newcodeverifyemail.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.gerotac.auth.newcodeverifyemail.domain.model.ReturnNewCode

data class ReturnNewCodeDto(
    @SerializedName("message") val message: String,
    @SerializedName("state") val state: Boolean,
    @SerializedName("status") val status: String
)

fun ReturnNewCodeDto.toNewCode(): ReturnNewCode {
    return ReturnNewCode(
        message = message,
        state = state,
        status = status
    )
}
