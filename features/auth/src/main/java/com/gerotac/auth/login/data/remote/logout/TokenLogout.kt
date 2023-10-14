package com.gerotac.auth.login.data.remote.logout

import com.google.gson.annotations.SerializedName

data class TokenLogout(
    @SerializedName("token")
    val token: String,
)
