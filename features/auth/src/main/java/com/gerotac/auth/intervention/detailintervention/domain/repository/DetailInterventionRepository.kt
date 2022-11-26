package com.gerotac.auth.intervention.detailintervention.domain.repository

import com.gerotac.auth.intervention.detailintervention.domain.model.DetailResponseIntervention
import com.gerotac.shared.network.Resource

interface DetailInterventionRepository {
    suspend fun getDetailIntervention(token: String,id: Int): Resource<DetailResponseIntervention>
}