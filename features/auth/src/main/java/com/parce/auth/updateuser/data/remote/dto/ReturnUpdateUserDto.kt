package com.parce.auth.updateuser.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.parce.auth.updateuser.domain.model.ReturnUpdateUser
import com.parce.auth.updateuser.domain.model.ParameterUpdateUser
import com.parce.auth.updateuser.domain.model.ReturnUser

import java.io.Serializable

data class ReturnUpdateUserDto(
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: String,
    @SerializedName("user") val user: UserDto
) : Serializable

fun ReturnUpdateUserDto.toUpdateUser(): ReturnUpdateUser {
    return ReturnUpdateUser(
        message = message,
        status = status,
        user = ReturnUser(
            user.birthday,
            user.type_document,
            user.document,
            user.gener,
            user.group_etnic,
            user.name,
            user.phone,
            user.presents_disability,
            user.academic_program,
            user.role,
            user.created_at,
            user.updated_at
        )
    )
}
