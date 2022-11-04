package com.gerotac.auth.register.domain.repository

import com.gerotac.auth.register.data.remote.dto.RegisterDto
import com.gerotac.auth.register.data.remote.dto.TokenRegisterDto
import com.gerotac.auth.register.domain.model.Authorization
import com.gerotac.shared.network.Resource

interface RegisterRepository {
    suspend fun submitEmail(email:String):Resource<Unit>
    suspend fun doRegister(register: RegisterDto): TokenRegisterDto
    suspend fun insertTokenRegister(token: Authorization)
}
