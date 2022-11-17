package com.gerotac.auth.dropdownapi.dropdown.presentation.state

import com.gerotac.auth.dropdownapi.dropdown.domain.model.areainterventions.ResultArea

data class ListAreaState(
    val isLoading: Boolean = false,
    val areaState: List<ResultArea> = emptyList(),
    val error: String = "",
    val message: String = ""
)