package com.gerotac.auth.login.data.repository

import com.gerotac.auth.login.data.remote.LoginApi
import com.gerotac.auth.login.data.remote.logindto.LoginDto
import com.gerotac.auth.login.data.remote.returnlogindto.ReturnLoginDto
import com.gerotac.auth.login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val api: LoginApi) : LoginRepository {
    override suspend fun doLogin(login: LoginDto): ReturnLoginDto {
        return api.doLogin(login)
    }
}
