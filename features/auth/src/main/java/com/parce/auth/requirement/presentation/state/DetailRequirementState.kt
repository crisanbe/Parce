package com.parce.auth.requirement.presentation.state

import com.parce.auth.requirement.domain.model.detailrequirement.Data

data class DetailRequirementState(
    val isLoading: Boolean = false,
    val detailRequirement: Data? = null,
    val error: String = "",
    val message: String = ""
)
