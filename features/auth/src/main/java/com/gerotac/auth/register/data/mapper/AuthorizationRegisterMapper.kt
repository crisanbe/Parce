package com.gerotac.auth.register.data.mapper

import com.gerotac.auth.register.data.local.entity.TokenRegisterEntity
import com.gerotac.auth.register.domain.model.Authorization

fun Authorization.toTokenEntity(): TokenRegisterEntity {
    return TokenRegisterEntity(
        expiration = expiration.toString(),
        token = token,
        type = type
    )
}
