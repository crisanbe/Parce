package com.parce.auth.requirement.presentation.state

import com.parce.auth.requirement.domain.model.RequirementReply
import com.parce.auth.requirement.domain.model.getrequirement.GetRequirement
import com.parce.auth.requirement.domain.model.getrequirement.Result

data class RequirementState(
    val isLoading: Boolean = false,
    val requirement: RequirementReply? = null,
    val error: String = "",
    val message: String = ""
)
