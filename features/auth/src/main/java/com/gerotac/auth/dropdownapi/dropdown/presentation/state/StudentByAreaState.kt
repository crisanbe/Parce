package com.gerotac.auth.dropdownapi.dropdown.presentation.state

import com.gerotac.auth.dropdownapi.dropdown.domain.model.studentbyarea.ResultStudent

data class StudentAreaState(
    val isLoading: Boolean = false,
    val studentByAreaState: List<ResultStudent> = emptyList(),
    val error: String = "",
    val message: String = ""
)