package com.gerotac.auth.requirement.presentation.state

import com.gerotac.auth.requirement.domain.model.getrequirement.Pagination

data class GetPageState(
    val isLoading: Boolean = false,
    val pagination: Pagination? = null,
    val error: String = "",
    val message: String = ""
)
