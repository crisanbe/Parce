package com.parce.auth.newcode.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.parce.auth.newcode.domain.model.ReturnNewCode

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
