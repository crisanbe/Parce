package com.parce.auth.codeverificationRegister.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ParameterCodeConfirmationDto(
    @SerializedName("confirmation_code") val confirmation_code: String
)
