package com.gerotac.auth.login.data.remote.returnlogindto

import com.google.gson.annotations.SerializedName
import com.gerotac.auth.login.domain.model.RetunTokenLogin
import java.io.Serializable

data class ReturnLoginDto(
    @SerializedName("authorization") val authorization: Authorization,
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String,
    @SerializedName("state") val state: Boolean,
    @SerializedName("status") val status: String,
    @SerializedName("user") val user: User
) : Serializable

fun ReturnLoginDto.toTokenLogin(): RetunTokenLogin {
    return RetunTokenLogin(
        authorization = com.gerotac.auth.login.domain.model.Authorization(
            authorization.expiration,
            authorization.token,
            authorization.type
        ),
        code = code,
        message = message,
        state = state,
        status = status,
        user = com.gerotac.auth.login.domain.model.User(
            user.name,
            user.email,
            user.role,
            user.user_status,
            user.email_verified,
            user.updated_at
        )
    )
}
