package com.gerotac.auth.requirement.presentation.state

import com.gerotac.auth.requirement.domain.model.getrequirement.Result

data class GetRequirementState(
    val isLoading: Boolean = false,
    val getRequirement: List<Result> = emptyList(),
    val showPrevious: Boolean = false,
    val showNext: Boolean = false,
    val error: String = "",
    val message: String = ""
)
