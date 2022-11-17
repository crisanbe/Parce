package com.gerotac.auth.dropdownapi.dropdown.data.remote.response.studentbyarea

import com.gerotac.auth.dropdownapi.dropdown.domain.model.studentbyarea.DataStudentByArea
import com.gerotac.auth.dropdownapi.dropdown.domain.model.studentbyarea.GetStudent
import com.gerotac.auth.dropdownapi.dropdown.domain.model.studentbyarea.ResultStudent

data class GetStudentByArea(
    val code: String,
    val data: DataStudentByArea,
    val message: String,
    val state: Boolean,
    val status: String
)

fun GetStudentByArea.toResponseStudentByArea(): GetStudent {
    return GetStudent(
        code = code,
        data = DataStudentByArea(
            result = data.result.mapIndexed { _, result ->
                ResultStudent(
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