package com.gerotac.auth.validateCodeVerificationForgotPassword.di

import com.gerotac.auth.codeverificationRegister.di.ServiceBuilderGeneral
import com.gerotac.auth.validateCodeVerificationForgotPassword.data.remote.ValidateCodeVerificationForgotPasswordApi
import com.gerotac.auth.validateCodeVerificationForgotPassword.data.repository.ValidateCodeVerificationForgotPasswordImpl
import com.gerotac.auth.validateCodeVerificationForgotPassword.domain.repository.CodeForgotPasswordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleCodeVerificationForgotPassword {
    @Provides
    @Singleton
    fun providerCodeForgotPasswordRepository(): CodeForgotPasswordRepository {
        return ValidateCodeVerificationForgotPasswordImpl(
            ServiceBuilderGeneral.ServiceBuilderRetrofit
                .buildService(ValidateCodeVerificationForgotPasswordApi::class.java)
        )
    }
}