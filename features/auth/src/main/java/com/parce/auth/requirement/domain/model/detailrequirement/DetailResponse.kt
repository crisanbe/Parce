package com.parce.auth.requirement.domain.model.detailrequirement

import java.io.Serializable

data class DetailResponse(
    val data : DataResponse,
    val message: String,
    val status: Boolean
): Serializable
