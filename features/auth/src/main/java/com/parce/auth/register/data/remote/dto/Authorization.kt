package com.parce.auth.register.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Authorization(
    @SerializedName("expiration") val expiration: Int,
    @SerializedName("token") val token: String,
    @SerializedName("type") val type: String
) : java.io.Serializable
