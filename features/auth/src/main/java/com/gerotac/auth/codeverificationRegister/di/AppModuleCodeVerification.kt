package com.gerotac.auth.codeverificationRegister.di

import com.gerotac.auth.codeverificationRegister.data.remote.ConfirmationCodeApi
import com.gerotac.auth.codeverificationRegister.data.repository.ConfirmationCodeRepositoryImpl
import com.gerotac.auth.codeverificationRegister.domain.repository.VerificationCodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleCodeVerification {
    @Provides
    @Singleton
    fun provideCodeVerificationRepository(): VerificationCodeRepository {
        return ConfirmationCodeRepositoryImpl(
            ServiceBuilderGeneral.ServiceBuilderRetrofit
                .buildService(ConfirmationCodeApi::class.java)
        )
    }
}
