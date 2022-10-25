package com.parce.auth.requirement.domain.repository

import com.parce.auth.requirement.domain.model.detailrequirement.Data
import com.parce.auth.requirement.domain.model.detailrequirement.DetailResponse
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow

interface DetailRequirementRepository {
    suspend fun doDetailRequirement(
        token: String,
        id: Int
    ): Resource<Data>
}