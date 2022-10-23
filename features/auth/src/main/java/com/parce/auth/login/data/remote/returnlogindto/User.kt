package com.parce.auth.login.data.remote.returnlogindto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String,
    @SerializedName("user_status") val user_status: String,
    @SerializedName("email_verified") val email_verified: String,
    @SerializedName("updated_at") val updated_at: String
) : Serializable
