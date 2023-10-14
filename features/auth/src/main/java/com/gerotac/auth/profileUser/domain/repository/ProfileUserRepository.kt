package com.gerotac.auth.profileUser.domain.repository

import com.gerotac.auth.profileUser.domain.model.User
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow

interface ProfileUserRepository {
     fun getProfileUser(token: String) : Flow<Resource<List<User>>>
}
