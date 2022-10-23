package com.parce.auth.profileUser.domain.repository

import com.parce.auth.profileUser.data.remote.response.ResponseProfileUser
import com.parce.auth.profileUser.domain.model.User
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow

interface ProfileUserRepository {
     fun getProfileUser(token: String) : Flow<Resource<List<User>>>
}
