package com.parce.auth.requirement.presentation.state

import com.parce.auth.requirement.domain.model.requirement.RequirementReply

data class RequirementState(
    val isLoading: Boolean = false,
    val requirement: RequirementReply? = null,
    val error: String = "",
    val message: String = ""
)
