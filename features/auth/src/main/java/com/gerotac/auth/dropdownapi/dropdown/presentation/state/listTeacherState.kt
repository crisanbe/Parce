package com.gerotac.auth.dropdownapi.dropdown.presentation.state

import com.gerotac.auth.dropdownapi.dropdown.domain.model.listateacher.ResultTeacher

data class ListTeacherState(
    val isLoading: Boolean = false,
    val teacherState: List<ResultTeacher> = emptyList(),
    val error: String = "",
    val message: String = ""
)