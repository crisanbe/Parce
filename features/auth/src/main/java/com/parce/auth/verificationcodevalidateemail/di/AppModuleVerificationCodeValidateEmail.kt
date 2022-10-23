package com.parce.auth.verificationcodevalidateemail.di

import com.parce.auth.codeverificationRegister.di.ServiceBuilderGeneral
import com.parce.auth.verificationcodevalidateemail.data.remote.VerificationCodeValidateEmailApi
import com.parce.auth.verificationcodevalidateemail.data.repository.ConfirmationCodeTokenRepositoryImpl
import com.parce.auth.verificationcodevalidateemail.data.repository.VerificationCodeValidateEmailRepositoryImpl
import com.parce.auth.verificationcodevalidateemail.domain.repository.VerificationCodeTokenRepository
import com.parce.auth.verificationcodevalidateemail.domain.repository.VerificationCodeValidateEmailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleVerificationCodeValidateEmail {
    @Provides
    @Singleton
    fun provideCodeVerificationRepository(): VerificationCodeValidateEmailRepository{
        return VerificationCodeValidateEmailRepositoryImpl(
            ServiceBuilderGeneral.ServiceBuilderRetrofit
                .buildService(VerificationCodeValidateEmailApi::class.java)
        )
    }

    @Provides
    @Singleton
    fun provideCodeVerificationTokenRepository(): VerificationCodeTokenRepository{
        return ConfirmationCodeTokenRepositoryImpl(
            ServiceBuilderGeneral.ServiceBuilderRetrofit
                .buildService(VerificationCodeValidateEmailApi::class.java)
        )
    }
}
