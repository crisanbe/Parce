package com.parce.auth.register.domain.repository

import com.parce.auth.register.data.remote.dto.RegisterDto
import com.parce.auth.register.data.remote.dto.TokenRegisterDto
import com.parce.auth.register.domain.model.Authorization
import com.parce.shared.network.Resource

interface RegisterRepository {
    suspend fun submitEmail(email:String):Resource<Unit>
    suspend fun doRegister(register: RegisterDto): TokenRegisterDto
    suspend fun insertTokenRegister(token: Authorization)
}
