package com.gerotac.auth.profileUser.domain.usecase

import com.gerotac.auth.profileUser.domain.model.User
import com.gerotac.auth.profileUser.domain.repository.ProfileUserRepository
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUserUseCase @Inject constructor(
    private val repository: ProfileUserRepository) {
    operator fun invoke(token: String): Flow<Resource<List<User>>> {
       return repository.getProfileUser(token = token)
    }
}
