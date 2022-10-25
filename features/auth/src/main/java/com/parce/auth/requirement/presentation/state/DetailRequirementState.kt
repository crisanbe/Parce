package com.parce.auth.requirement.presentation.state

import com.parce.auth.requirement.domain.model.detailrequirement.DataResponse

data class DetailRequirementState(
    val detailRequirement: DataResponse? = null,
    val isLoading: Boolean = false
)
