package com.gerotac.auth.updateuser.di

import com.gerotac.auth.codeverificationRegister.di.ServiceBuilderGeneral
import com.gerotac.auth.updateuser.data.remote.UpdateUserApi
import com.gerotac.auth.updateuser.data.repository.UpdateUserRepositoryImpl
import com.gerotac.auth.updateuser.domain.repository.UpdateUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleUpdateUser {
    @Provides
    @Singleton
    fun provideCodeVerificationRepository(): UpdateUserRepository {
        return UpdateUserRepositoryImpl(
            ServiceBuilderGeneral.ServiceBuilderRetrofit
                .buildService(UpdateUserApi::class.java)
        )
    }
}
