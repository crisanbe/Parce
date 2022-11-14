package com.gerotac.auth.requirement.domain.repository

import com.gerotac.auth.requirement.domain.model.detailrequirement.Data
import com.gerotac.shared.network.Resource

interface DetailRequirementRepository {
    suspend fun doDetailRequirement(token: String, id: Int): Resource<Data>
}