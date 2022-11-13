package com.gerotac.auth.requirement.domain.usecase

import com.gerotac.auth.requirement.domain.model.detailrequirement.Result
import com.gerotac.auth.requirement.domain.repository.DetailRequirementRepository
import com.gerotac.shared.network.Resource
import javax.inject.Inject

class DetailRequirementUseCase @Inject constructor(
    private val repository: DetailRequirementRepository
) {
    suspend operator fun invoke(token: String, id: Int): Resource<Result> {
        return repository.doDetailRequirement(token = token, id = id)
    }
}
