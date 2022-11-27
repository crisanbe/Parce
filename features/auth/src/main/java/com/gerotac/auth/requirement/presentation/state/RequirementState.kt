package com.gerotac.auth.requirement.presentation.state

import com.gerotac.auth.requirement.data.remote.requirementsave.RequirementResponse

data class RequirementState(
    val isLoading: Boolean = false,
    val requirement: RequirementResponse? = null,
    val error: String = "",
    val message: String = ""
)
