package com.gerotac.auth.verificationcodevalidateemail.di

import com.gerotac.auth.codeverificationRegister.di.ServiceBuilderGeneral
import com.gerotac.auth.verificationcodevalidateemail.data.remote.VerificationCodeValidateEmailApi
import com.gerotac.auth.verificationcodevalidateemail.data.repository.ConfirmationCodeTokenRepositoryImpl
import com.gerotac.auth.verificationcodevalidateemail.data.repository.VerificationCodeValidateEmailRepositoryImpl
import com.gerotac.auth.verificationcodevalidateemail.domain.repository.VerificationCodeTokenRepository
import com.gerotac.auth.verificationcodevalidateemail.domain.repository.VerificationCodeValidateEmailRepository
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
