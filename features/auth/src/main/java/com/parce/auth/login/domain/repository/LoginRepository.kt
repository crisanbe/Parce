package com.parce.auth.login.domain.repository

import com.parce.auth.login.data.remote.logindto.LoginDto
import com.parce.auth.login.data.remote.returnlogindto.ReturnLoginDto

interface LoginRepository {
    suspend fun doLogin(login: LoginDto): ReturnLoginDto
}
