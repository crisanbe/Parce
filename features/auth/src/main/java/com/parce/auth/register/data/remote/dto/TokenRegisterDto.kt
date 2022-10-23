package com.parce.auth.register.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.parce.auth.register.domain.model.RetunTokenRegister

data class TokenRegisterDto(
    @SerializedName("authorization") val authorization: Authorization,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("state") val state: Boolean,
    @SerializedName("status") val status: String,
    @SerializedName("user") val user: User
) : java.io.Serializable


fun TokenRegisterDto.toTokenRegis(): RetunTokenRegister {
    return RetunTokenRegister(
        authorization = com.parce.auth.register.domain.model.Authorization(
            authorization.expiration,
            authorization.token,
            authorization.type
        ),
        code = code,
        message = message,
        state = state,
        status = status,
        user = com.parce.auth.register.domain.model.User(
            user.created_at,
            user.email,
            user.id,
            user.name,
            user.role,
            user.updated_at
        )
    )
}
