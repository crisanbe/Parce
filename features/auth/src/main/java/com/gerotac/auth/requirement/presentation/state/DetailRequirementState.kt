package com.gerotac.auth.requirement.presentation.state

import com.gerotac.auth.requirement.domain.model.detailrequirement.Data
import com.gerotac.auth.requirement.domain.model.detailrequirement.File

data class DetailRequirementState(
    val detailRequirement: Data? = null,
    val fileRequirement: List<File> = emptyList(),
    val isLoading: Boolean = false
)
