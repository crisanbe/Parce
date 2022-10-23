package com.parce.auth.register.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisterDto(
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("password") val password: String,
    @SerializedName("password_confirmation") val password_confirmation: String,
    @SerializedName("role") val role: String
)
