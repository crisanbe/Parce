package com.parce.auth.profileUser.domain.usecase

import com.parce.auth.profileUser.data.remote.api.GetProfileUserApi
import com.parce.auth.profileUser.data.remote.response.ResponseProfileUser
import com.parce.auth.profileUser.data.remote.response.toGetProfile
import com.parce.auth.profileUser.domain.model.User
import com.parce.auth.profileUser.domain.repository.ProfileUserRepository
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProfileUserUseCase @Inject constructor(
    private val repository: ProfileUserRepository) {
    operator fun invoke(token: String): Flow<Resource<List<User>>> {
       return repository.getProfileUser(token = token)
    }
}
