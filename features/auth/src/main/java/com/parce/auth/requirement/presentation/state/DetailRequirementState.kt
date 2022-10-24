package com.parce.auth.requirement.presentation.state

import com.parce.auth.requirement.domain.model.detailrequirement.GetDetailResponse

data class DetailRequirementState(
    val isLoading: Boolean = false,
    val detailRequirement: GetDetailResponse? = null,
    val error: String = "",
    val message: String = ""
)
