package com.parce.auth.requirement.domain.model.detailrequirement

import java.io.Serializable

data class GetDetailResponse(
    val `data`: Data,
    val message: String,
    val status: Boolean
): Serializable
