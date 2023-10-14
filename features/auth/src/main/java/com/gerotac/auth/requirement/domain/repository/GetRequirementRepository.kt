package com.gerotac.auth.requirement.domain.repository

import com.gerotac.auth.requirement.domain.model.getrequirement.Pagination
import com.gerotac.auth.requirement.domain.model.getrequirement.Result
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow

interface GetRequirementRepository {
    fun doGetRequirement(
        token: String,
        current_page: Int,
    ): Flow<Resource<List<Result>>>

    fun doGetPagination(
        token: String
    ): Flow<Resource<Pagination>>
}