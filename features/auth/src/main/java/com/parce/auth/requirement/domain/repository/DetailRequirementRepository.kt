package com.parce.auth.requirement.domain.repository

import com.parce.auth.requirement.domain.model.detailrequirement.DataResponse
import com.parce.shared.network.Resource

interface DetailRequirementRepository {
    suspend fun doDetailRequirement(token: String, id: Int): Resource<DataResponse>
}