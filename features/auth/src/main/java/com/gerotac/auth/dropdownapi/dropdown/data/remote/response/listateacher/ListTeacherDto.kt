package com.gerotac.auth.dropdownapi.dropdown.data.remote.response.listateacher

import com.gerotac.auth.dropdownapi.dropdown.domain.model.listateacher.Data
import com.gerotac.auth.dropdownapi.dropdown.domain.model.listateacher.ListTeacher
import com.gerotac.auth.dropdownapi.dropdown.domain.model.listateacher.ResultTeacher

data class ListTeacherDto(
    val code: String,
    val data: Data,
    val message: String,
    val state: Boolean,
    val status: String
)

fun ListTeacherDto.toResponseListTeacher(): ListTeacher {
    return ListTeacher(
        code = code,
        data = Data(
            result = data.result.mapIndexed { _, result ->
                ResultTeacher(
                    id = result.id,
                    name = result.name,
                )
            }
        ),
        message = message,
        state = state,
        status = status
    )
}