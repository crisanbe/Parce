package com.parce.auth.requirement.presentation.state

import com.parce.auth.requirement.domain.model.RequirementReply
import com.parce.auth.requirement.domain.model.getrequirement.GetRequirement
import com.parce.auth.requirement.domain.model.getrequirement.Pagination
import com.parce.auth.requirement.domain.model.getrequirement.Result

data class GetRequirementState(
    val isLoading: Boolean = false,
    val getRequirement: List<Result> = emptyList(),
    val showPrevious: Boolean = false,
    val showNext: Boolean = false,
    val error: String = "",
    val message: String = ""
)
