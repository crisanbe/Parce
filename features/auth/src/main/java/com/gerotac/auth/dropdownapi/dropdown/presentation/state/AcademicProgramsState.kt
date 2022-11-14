package com.gerotac.auth.dropdownapi.dropdown.presentation.state

import com.gerotac.auth.dropdownapi.dropdown.domain.model.dropmodel.Result

data class AcademicProgramsState(
    val isLoading: Boolean = false,
    val academicProgramsState: List<Result> = emptyList(),
    val error: String = "",
    val message: String = ""
)