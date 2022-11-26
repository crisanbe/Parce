package com.gerotac.auth.intervention.getintervention.domain.usecase

import com.gerotac.auth.intervention.getintervention.domain.model.getdetailrequirementinterventions.Intervention
import com.gerotac.auth.intervention.getintervention.domain.repository.DetailRequirementInterventionRepository
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInterventionOfDetailUseCase @Inject constructor(
    private val repository: DetailRequirementInterventionRepository
) {
    operator fun invoke(token: String): Flow<Resource<List<Intervention>>> {
        return repository.getDetailInterventionOfRequirement(token)
    }
}