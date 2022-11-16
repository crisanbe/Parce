package com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.response

import com.gerotac.auth.assignrequirement.domain.model.assignrequirement.response.Assign
import com.gerotac.auth.assignrequirement.domain.model.assignrequirement.response.Data

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
