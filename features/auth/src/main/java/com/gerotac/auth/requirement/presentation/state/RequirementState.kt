package com.gerotac.auth.requirement.presentation.state

import com.gerotac.auth.requirement.domain.model.RequirementReply

data class RequirementState(
    val isLoading: Boolean = false,
    val requirement: RequirementReply? = null,
    val error: String = "",
    val message: String = ""
)
