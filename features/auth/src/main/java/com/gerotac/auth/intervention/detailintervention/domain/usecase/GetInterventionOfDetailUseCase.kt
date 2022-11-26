package com.gerotac.auth.intervention.detailintervention.domain.usecase

import com.gerotac.auth.intervention.detailintervention.domain.model.DetailResponseIntervention
import com.gerotac.auth.intervention.detailintervention.domain.repository.DetailInterventionRepository
import com.gerotac.shared.network.Resource
import javax.inject.Inject

class GetInterventionOfDetailUseCase @Inject constructor(
    private val repository: DetailInterventionRepository
) {
    suspend operator fun invoke(token: String, id: Int): Resource<DetailResponseIntervention> {
        return repository.getDetailIntervention(token = token, id = id)
    }
}