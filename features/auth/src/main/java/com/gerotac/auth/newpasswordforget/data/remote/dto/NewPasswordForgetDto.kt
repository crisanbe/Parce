package com.gerotac.auth.newpasswordforget.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NewPasswordForgetDto(
    @SerializedName("token") val token: String,
    @SerializedName("password") val password: String,
    @SerializedName("confirmed_password") val confirmed_password: String
)
