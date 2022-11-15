package com.gerotac.auth.requirement.data.remote.assignrequirement.response

import com.gerotac.auth.requirement.domain.model.assignrequirement.response.Assign
import com.gerotac.auth.requirement.domain.model.assignrequirement.response.Data

data class AssignDto(
    val data: Data,
    val message: String,
    val status: Boolean
)

fun AssignDto.toGetTeacher(): Assign {
    return Assign(
        data = data,
        message = message,
        status = status
    )
}
