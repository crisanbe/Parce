package com.gerotac.auth.requirement.presentation.state

import com.gerotac.auth.requirement.domain.model.detailrequirement.Data

data class DetailRequirementState(
    val detailRequirement: Data? = null,
    val isLoading: Boolean = false
)
