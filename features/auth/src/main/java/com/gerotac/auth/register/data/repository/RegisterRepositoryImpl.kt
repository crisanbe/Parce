package com.gerotac.auth.register.data.repository

import com.gerotac.auth.register.data.local.DaoTokenRegister
import com.gerotac.auth.register.data.mapper.toTokenEntity
import com.gerotac.auth.register.data.remote.RegisterApi
import com.gerotac.auth.register.data.remote.dto.RegisterDto
import com.gerotac.auth.register.data.remote.dto.TokenRegisterDto
import com.gerotac.auth.register.domain.model.Authorization
import com.gerotac.auth.register.domain.repository.RegisterRepository
import com.gerotac.shared.network.Resource
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random

class RegisterRepositoryImpl @Inject constructor(
    private val dao: DaoTokenRegister,
    private val api: RegisterApi) :
    RegisterRepository {
    override suspend fun submitEmail(email: String): Resource<Unit> {
        delay(500L)
        return if (Random.nextBoolean()){
            Resource.Success(Unit)
        }else{
            if (Random.nextBoolean()){
                Resource.Error("Server error")
            }else{
                Resource.Error("Not Authentication error")
            }
        }
    }


    override suspend fun doRegister(register: RegisterDto): TokenRegisterDto {
        return api.doRegister(register)
    }

    override suspend fun insertTokenRegister(token: Authorization) {
        dao.insert(token.toTokenEntity())
    }
}
