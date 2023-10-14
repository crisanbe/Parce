package com.gerotac.auth.register.data.remote.dto

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("created_at") val created_at: String,
    @SerializedName("email") val email: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("role") val role: String,
    @SerializedName("updated_at") val updated_at: String
) : java.io.Serializable
