package com.gerotac.auth.requirement.domain.usecase

import com.gerotac.auth.requirement.domain.model.getrequirement.Pagination
import com.gerotac.auth.requirement.domain.repository.GetRequirementRepository
import com.gerotac.shared.network.Resource
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
