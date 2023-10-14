package com.gerotac.auth.assignrequirement.presentation.state

import com.gerotac.auth.assignrequirement.data.remote.assignteacherdto.assignrequirement.response.AssignDto

data class AssignTeacherState(
    val isLoading: Boolean = false,
    val assignTeacher: AssignDto? = null,
    val error: String = "",
    val message: String = ""
)
