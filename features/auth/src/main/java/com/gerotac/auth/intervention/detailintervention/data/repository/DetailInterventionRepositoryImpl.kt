package com.gerotac.auth.intervention.detailintervention.data.repository

import com.gerotac.auth.intervention.detailintervention.data.remote.api.GetDetailInterventionApi
import com.gerotac.auth.intervention.detailintervention.data.remote.responsedetailintervention.toGetDetailIntervention
import com.gerotac.auth.intervention.detailintervention.domain.model.DetailResponseIntervention
import com.gerotac.auth.intervention.detailintervention.domain.repository.DetailInterventionRepository
import com.gerotac.auth.requirement.domain.model.detailrequirement.Data
import com.gerotac.auth.requirement.domain.repository.DetailRequirementRepository
import com.gerotac.shared.network.Resource
import javax.inject.Inject

class DetailInterventionRepositoryImpl @Inject constructor(
    private val api: GetDetailInterventionApi,
) : DetailInterventionRepository {

    override suspend fun getDetailIntervention(
        token: String,
        id: Int
    ): Resource<DetailResponseIntervention> {
        val response = try {
            api.doGetDetailInterventionApi(token = token, id = id)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred")
        }
        return Resource.Success(response.toGetDetailIntervention())
    }
}
