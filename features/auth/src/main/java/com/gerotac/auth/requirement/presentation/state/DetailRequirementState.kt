package com.gerotac.auth.requirement.presentation.state

import com.gerotac.auth.requirement.domain.model.detailrequirement.DataResponse

data class DetailRequirementState(
    val detailRequirement: DataResponse? = null,
    val isLoading: Boolean = false
)
