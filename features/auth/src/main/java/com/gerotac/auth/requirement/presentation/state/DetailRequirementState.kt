package com.gerotac.auth.requirement.presentation.state

import com.gerotac.auth.requirement.domain.model.detailrequirement.File
import com.gerotac.auth.requirement.domain.model.detailrequirement.Result

data class DetailRequirementState(
    val detailRequirement: Result? = null,
    val fileRequirement: List<File> = emptyList(),
    val isLoading: Boolean = false
)
