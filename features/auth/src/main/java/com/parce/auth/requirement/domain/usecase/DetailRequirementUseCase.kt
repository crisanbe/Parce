package com.parce.auth.requirement.domain.usecase

import com.parce.auth.requirement.domain.model.detailrequirement.DataResponse
import com.parce.auth.requirement.domain.repository.DetailRequirementRepository
import com.parce.shared.network.Resource
import javax.inject.Inject

class DetailRequirementUseCase @Inject constructor(
    private val repository: DetailRequirementRepository
) {
    suspend operator fun invoke(token: String, id: Int): Resource<DataResponse> {
        return repository.doDetailRequirement(
            token = token,
            id = id
        )
    }
}
