package com.gerotac.auth.requirement.presentation.state

import com.gerotac.auth.requirement.domain.model.assignrequirement.response.Assign

data class AssignTeacherState(
    val isLoading: Boolean = false,
    val assignTeacher: Assign? = null,
    val error: String = "",
    val message: String = ""
)
