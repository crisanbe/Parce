package com.gerotac.auth.dropdownapi.dropdown.presentation.state

import com.gerotac.auth.dropdownapi.dropdown.domain.model.responsecompany.ResultCompany

data class CompanyState(
    val isLoading: Boolean = false,
    val companyState: List<ResultCompany> = emptyList(),
    val error: String = "",
    val message: String = ""
)