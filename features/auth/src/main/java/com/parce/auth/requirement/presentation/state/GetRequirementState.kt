package com.parce.auth.requirement.presentation.state

import com.parce.auth.requirement.domain.model.getrequirement.Result

data class GetRequirementState(
    val isLoading: Boolean = false,
    val getRequirement: List<Result> = emptyList(),
    val input: String = "",
    val page: Int = 1,
    val showPrevious: Boolean = false,
    val showNext: Boolean = false,
    val error: String = "",
    val message: String = ""
)
