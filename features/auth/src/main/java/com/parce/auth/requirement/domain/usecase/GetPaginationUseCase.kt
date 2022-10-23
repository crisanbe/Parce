package com.parce.auth.requirement.domain.usecase

import com.parce.auth.requirement.domain.model.getrequirement.GetRequirement
import com.parce.auth.requirement.domain.model.getrequirement.Pagination
import com.parce.auth.requirement.domain.model.getrequirement.Result
import com.parce.auth.requirement.domain.repository.GetRequirementRepository
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPaginationUseCase @Inject constructor(
    private val repository: GetRequirementRepository
) {
    operator fun invoke(
        token: String
    ): Flow<Resource<Pagination>> {
        return repository.doGetPagination(
            token = token
        )
    }
}
