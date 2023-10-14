package com.gerotac.auth.login.domain.repository

import com.gerotac.auth.login.data.remote.logindto.LoginDto
import com.gerotac.auth.login.data.remote.returnlogindto.ReturnLoginDto

interface LoginRepository {
    suspend fun doLogin(login: LoginDto): ReturnLoginDto
}
