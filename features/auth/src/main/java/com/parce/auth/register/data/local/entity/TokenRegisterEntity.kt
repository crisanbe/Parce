package com.parce.auth.register.data.local.entity

import androidx.room.*

@Entity(tableName = "TokenRegisterEntity")
data class TokenRegisterEntity(
    val expiration: String,
    val token: String?,
    val type: String,
    @PrimaryKey var id: Int? = null
)
