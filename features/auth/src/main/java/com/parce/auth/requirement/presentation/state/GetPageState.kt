package com.parce.auth.requirement.presentation.state

import com.parce.auth.requirement.domain.model.getrequirement.Pagination

data class GetPageState(
    val isLoading: Boolean = false,
    val pagination: Pagination? = null,
    val error: String = "",
    val message: String = ""
)
