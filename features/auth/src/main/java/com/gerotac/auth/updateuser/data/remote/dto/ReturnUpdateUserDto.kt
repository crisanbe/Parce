@file:Suppress("UselessCallOnNotNull")

package com.gerotac.auth.updateuser.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.gerotac.auth.updateuser.domain.model.ReturnUpdateUser
import com.gerotac.auth.updateuser.domain.model.ReturnUser

import java.io.Serializable

data class ReturnUpdateUserDto(
    @SerializedName("message") val message: String? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("user") val user: UserDto? = null
) : Serializable

