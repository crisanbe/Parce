package com.parce.auth.requirement.domain.model.detailrequirement

import java.io.Serializable

data class File(
    val created_at: String,
    val id: Int,
    val url: String
): Serializable