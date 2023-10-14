package com.gerotac.auth.protodata

import androidx.datastore.core.DataStore
import com.gerotac.auth.UserStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class ProtoUserRepoImpl(
    private val protoDataStore: DataStore<UserStore>,
) : ProtoUserRepo {
    /*  override suspend fun saveUserLoggedInState(state: Boolean) {
          protoDataStore.updateData { store ->
              store.toBuilder()
                  .setIsLoggedIn(state)
                  .build()
          }
      }

      override suspend fun getUserLoggedState(): Flow<Boolean> {
          return protoDataStore.data
              .catch { exp ->
                  if (exp is IOException) {
                      emit(UserStore.getDefaultInstance())
                  } else {
                      throw exp
                  }
              }.map { protoBuilder ->
                  protoBuilder.isLoggedIn
              }
      }*/

    override suspend fun saveTokenLoginState(token: String?) {
        protoDataStore.updateData { toke ->
            toke.toBuilder()
                .setTokenAutentication(token)
                .build()
        }
    }

    override suspend fun getTokenLoginState(): Flow<String> {
        return protoDataStore.data
            .catch { exp ->
                if (exp is IOException) {
                    emit(UserStore.getDefaultInstance())
                } else {
                    throw exp
                }
            }.map { protoBuilder ->
                protoBuilder.tokenAutentication
            }
    }

    override suspend fun deleteTokenLoginState() {
        protoDataStore.updateData {
            it.toBuilder()
                .clearTokenAutentication()
                .build()
        }
    }

    override suspend fun saveRolLogin(token: String?) {
        protoDataStore.updateData { toke ->
            toke.toBuilder()
                .setRol(token)
                .build()
        }
    }

    override suspend fun getRolLogin(): Flow<String> {
        return protoDataStore.data
            .catch { exp ->
                if (exp is IOException) {
                    emit(UserStore.getDefaultInstance())
                } else {
                    throw exp
                }
            }.map { protoBuilder ->
                protoBuilder.rol
            }
    }

    override suspend fun deleteRolLogin() {
        protoDataStore.updateData {
            it.toBuilder()
                .clearRol()
                .build()
        }
    }

    override suspend fun saveTokenRegister(token: String?) {
        protoDataStore.updateData { toke ->
            toke.toBuilder()
                .setTokenRegister(token)
                .build()
        }
    }

    override suspend fun getTokenRegister(): Flow<String> {
        return protoDataStore.data
            .catch { exp ->
                if (exp is IOException) {
                    emit(UserStore.getDefaultInstance())
                } else {
                    throw exp
                }
            }.map { protoBuilder ->
                protoBuilder.tokenRegister
            }
    }

    override suspend fun deleteTokenRegister() {
        protoDataStore.updateData {
            it.toBuilder()
                .clearTokenRegister()
                .build()
        }
    }

    override suspend fun saveEmailUser(email: String?) {
        protoDataStore.updateData { emai ->
            emai.toBuilder()
                .setEmailUser(email)
                .build()
        }
    }

    override suspend fun getEmailUser(): Flow<String> {
        return protoDataStore.data
            .catch { exp ->
                if (exp is IOException) {
                    emit(UserStore.getDefaultInstance())
                } else {
                    throw exp
                }
            }.map { protoBuilder ->
                protoBuilder.emailUser
            }
    }

    override suspend fun deleteEmailState() {
        protoDataStore.updateData {
            it.toBuilder()
                .clearEmailUser()
                .build()
        }
    }

    override suspend fun saveNameUser(status: String?) {
        protoDataStore.updateData {
            it.toBuilder()
                .setNameUser(status)
                .build()
        }
    }

    override suspend fun getNameUser(): Flow<String> {
        return protoDataStore.data
            .catch { exp ->
                if (exp is IOException) {
                    emit(UserStore.getDefaultInstance())
                } else {
                    throw exp
                }
            }.map { protoBuilder ->
                protoBuilder.nameUser
            }
    }

    override suspend fun savePages(page: Int?) {
        protoDataStore.updateData { pages ->
            pages.toBuilder()
                .setPages(page!!)
                .build()
        }
    }

    override suspend fun getPage(): Flow<Int> {
        return protoDataStore.data
            .catch { exp ->
                if (exp is IOException) {
                    emit(UserStore.getDefaultInstance())
                } else {
                    throw exp
                }
            }.map { protoBuilder ->
                protoBuilder.pages
            }
    }
}
