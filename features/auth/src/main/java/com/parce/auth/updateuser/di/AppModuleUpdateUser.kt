package com.parce.auth.updateuser.di

import com.parce.auth.codeverificationRegister.di.ServiceBuilderGeneral
import com.parce.auth.updateuser.data.remote.UpdateUserApi
import com.parce.auth.updateuser.data.repository.UpdateUserRepositoryImpl
import com.parce.auth.updateuser.domain.repository.UpdateUserRepository
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
