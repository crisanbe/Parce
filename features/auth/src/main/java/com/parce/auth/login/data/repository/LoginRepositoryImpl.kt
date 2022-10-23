package com.parce.auth.login.data.repository

import com.parce.auth.login.data.remote.LoginApi
import com.parce.auth.login.data.remote.logindto.LoginDto
import com.parce.auth.login.data.remote.returnlogindto.ReturnLoginDto
import com.parce.auth.login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val api: LoginApi) : LoginRepository {
    override suspend fun doLogin(login: LoginDto): ReturnLoginDto {
        return api.doLogin(login)
    }
}
