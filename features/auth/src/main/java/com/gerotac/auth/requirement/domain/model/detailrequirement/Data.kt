package com.gerotac.auth.requirement.domain.model.detailrequirement

import com.gerotac.auth.requirement.data.remote.getdetailrequirement.DataResult

data class Data(
    val data: DataResult,
    val message: String,
    val status: Boolean
)
