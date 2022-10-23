package com.parce.auth.dropdownapi.dropacademicprograms.presentation.state

import com.parce.auth.dropdownapi.dropacademicprograms.domain.model.locationmodel.Town

data class MuniState(
    val isLoading: Boolean = false,
    val muniState: List<Town> = emptyList(),
    val error: String = "",
    val message: String = ""
)