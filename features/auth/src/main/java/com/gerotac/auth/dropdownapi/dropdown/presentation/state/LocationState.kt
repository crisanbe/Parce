package com.gerotac.auth.dropdownapi.dropdown.presentation.state

import com.gerotac.auth.dropdownapi.dropdown.domain.model.locationmodel.ResultX

data class LocationState(
    val isLoading: Boolean = false,
    val locationState: List<ResultX> = emptyList(),
    val error: String = "",
    val message: String = ""
)