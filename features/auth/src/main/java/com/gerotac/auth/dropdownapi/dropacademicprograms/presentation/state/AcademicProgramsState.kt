package com.gerotac.auth.dropdownapi.dropacademicprograms.presentation.state

import com.gerotac.auth.dropdownapi.dropacademicprograms.domain.model.dropmodel.Result

data class AcademicProgramsState(
    val isLoading: Boolean = false,
    val academicProgramsState: List<Result> = emptyList(),
    val error: String = "",
    val message: String = ""
)