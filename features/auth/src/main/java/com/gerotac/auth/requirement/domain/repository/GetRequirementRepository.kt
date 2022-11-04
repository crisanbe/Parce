package com.gerotac.auth.requirement.domain.repository

import com.gerotac.auth.requirement.domain.model.getrequirement.GetRequirement
import com.gerotac.auth.requirement.domain.model.getrequirement.Pagination
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow

interface GetRequirementRepository {
    fun doGetRequirement(
        token: String,
        current_page: Int,
        id : String? = null
    ): Flow<Resource<GetRequirement>>

    fun doGetPagination(
        token: String
    ): Flow<Resource<Pagination>>
}