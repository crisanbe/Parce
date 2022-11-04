package com.gerotac.auth.requirement.presentation.state

import com.gerotac.auth.requirement.domain.model.detailrequirement.DataResponse
import com.gerotac.auth.requirement.domain.model.detailrequirement.FileResponse

data class DetailRequirementState(
    val detailRequirement: DataResponse? = null,
    val fileRequirement: List<FileResponse> = emptyList(),
    val isLoading: Boolean = false
)
