package com.parce.auth.register.data.mapper

import com.parce.auth.register.data.local.entity.TokenRegisterEntity
import com.parce.auth.register.domain.model.Authorization

fun Authorization.toTokenEntity(): TokenRegisterEntity {
    return TokenRegisterEntity(
        expiration = expiration.toString(),
        token = token,
        type = type
    )
}
