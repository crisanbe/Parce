package com.gerotac.auth.dropdownapi.dropdown.presentation.state

import com.gerotac.auth.dropdownapi.dropdown.domain.model.responseacademic.ResultAcademic

data class AcademicProgramsState(
    val isLoading: Boolean = false,
    val academicProgramsState: List<ResultAcademic> = emptyList(),
    val error: String = "",
    val message: String = ""
)