package com.gerotac.auth.sendemailforgotmypassword.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SendEmailForgotMyPasswordDto(
    @SerializedName("email") val email: String
)
