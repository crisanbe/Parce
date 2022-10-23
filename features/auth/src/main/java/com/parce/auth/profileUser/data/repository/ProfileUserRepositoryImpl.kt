package com.parce.auth.profileUser.data.repository

import com.parce.auth.profileUser.data.remote.api.GetProfileUserApi
import com.parce.auth.profileUser.data.remote.response.toGetProfile
import com.parce.auth.profileUser.domain.model.User
import com.parce.auth.profileUser.domain.repository.ProfileUserRepository
import com.parce.shared.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProfileUserRepositoryImpl @Inject constructor(private val api: GetProfileUserApi) :
    ProfileUserRepository {
    override fun getProfileUser(token: String): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.doGetProfile(token = token).toGetProfile()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong",
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection",
                    data = null
                )
            )
        }
    }

}
