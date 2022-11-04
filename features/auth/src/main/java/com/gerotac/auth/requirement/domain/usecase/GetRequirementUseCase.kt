package com.gerotac.auth.requirement.domain.usecase

import com.gerotac.auth.requirement.domain.model.getrequirement.GetRequirement
import com.gerotac.auth.requirement.domain.repository.GetRequirementRepository
import com.gerotac.shared.network.Resource
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
