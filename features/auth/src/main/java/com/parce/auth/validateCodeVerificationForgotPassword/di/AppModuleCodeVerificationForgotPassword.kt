package com.parce.auth.validateCodeVerificationForgotPassword.di

import com.parce.auth.codeverificationRegister.di.ServiceBuilderGeneral
import com.parce.auth.validateCodeVerificationForgotPassword.data.remote.ValidateCodeVerificationForgotPasswordApi
import com.parce.auth.validateCodeVerificationForgotPassword.data.repository.ValidateCodeVerificationForgotPasswordImpl
import com.parce.auth.validateCodeVerificationForgotPassword.domain.repository.CodeForgotPasswordRepository
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