package com.gerotac.auth.intervention.getintervention.domain.repository

import com.gerotac.auth.intervention.getintervention.domain.model.getdetailrequirementinterventions.Intervention
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow

interface DetailRequirementInterventionRepository {

    fun getDetailInterventionOfRequirement(token:String): Flow<Resource<List<Intervention>>>

    //suspend fun getCharacter(id: Int): Result<Character>
}