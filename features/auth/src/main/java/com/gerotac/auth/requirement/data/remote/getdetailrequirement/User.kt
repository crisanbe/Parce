package com.gerotac.auth.requirement.data.remote.getdetailrequirement

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("name") val name: String,
    @SerializedName("role") val role: String
): Serializable