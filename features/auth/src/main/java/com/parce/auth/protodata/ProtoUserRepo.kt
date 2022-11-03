package com.parce.auth.protodata

import kotlinx.coroutines.flow.Flow

interface ProtoUserRepo {
    suspend fun saveTokenLoginState(token: String?)
    suspend fun getTokenLoginState(): Flow<String>
    suspend fun deleteTokenLoginState()
    suspend fun saveRolLogin(token: String?)
    suspend fun getRolLogin(): Flow<String>
    suspend fun deleteRolLogin()
    suspend fun saveEmailUser(email: String?)
    suspend fun getEmailUser(): Flow<String>
    suspend fun deleteEmailState()
    suspend fun saveTokenRegister(token: String?)
    suspend fun getTokenRegister(): Flow<String>
    suspend fun deleteTokenRegister()
    suspend fun saveNameUser(status: String?)
    suspend fun getNameUser(): Flow<String>
}
