package com.gerotac.auth.dropdownapi.dropdown.presentation.state

import com.gerotac.auth.dropdownapi.dropdown.domain.model.locationmodel.Town

data class MuniState(
    val isLoading: Boolean = false,
    val muniState: List<Town> = emptyList(),
    val error: String = "",
    val message: String = ""
)