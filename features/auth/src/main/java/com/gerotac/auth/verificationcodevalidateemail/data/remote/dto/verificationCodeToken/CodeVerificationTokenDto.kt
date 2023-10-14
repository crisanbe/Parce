package com.gerotac.auth.verificationcodevalidateemail.data.remote.dto.verificationCodeToken

import com.google.gson.annotations.SerializedName

data class CodeVerificationTokenDto(
    @SerializedName("confirmation_code") val confirmation_code: String
)
