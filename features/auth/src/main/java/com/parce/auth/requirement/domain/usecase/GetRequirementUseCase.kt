package com.parce.auth.requirement.domain.usecase

import com.parce.auth.requirement.domain.model.getrequirement.GetRequirement
import com.parce.auth.requirement.domain.model.getrequirement.Result
import com.parce.auth.requirement.domain.repository.GetRequirementRepository
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRequirementUseCase @Inject constructor(
    private val repository: GetRequirementRepository
) {
    operator fun invoke(
        token: String,
        current_page: Int,
        id: String? = null
    ): Flow<Resource<GetRequirement>> {
        return repository.doGetRequirement(
            token = token,
            current_page = current_page,
            id = id
        )
    }
}
