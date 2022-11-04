package com.gerotac.auth.login.data.remote.returnlogindto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Authorization(
    @SerializedName("expiration") val expiration: Int,
    @SerializedName("token") val token: String,
    @SerializedName("type") val type: String
) : Serializable
