package com.parce.auth.sendemailforgotmypassword.di

import com.parce.auth.sendemailforgotmypassword.data.remote.SendEmailForgotMyPasswordApi
import com.parce.auth.sendemailforgotmypassword.data.repository.SendEmailForgotMyPasswordRepositoryImpl
import com.parce.auth.sendemailforgotmypassword.domain.repository.SendEmailForgotMyPasswordRepository
import com.parce.network.ServiceBuilder
import com.parce.shared.commons.Constant
import com.parce.shared.commons.Header
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleSendEmailForgotMyPassword {
    @Provides
    @Singleton
    fun provideHeadersForgotMyPassword(): Map<String, String> = Header.headers
    @Provides
    @Singleton
    fun provideForgotMyPasswordRepository(): SendEmailForgotMyPasswordRepository {
        return SendEmailForgotMyPasswordRepositoryImpl(
            ServiceBuilder.createService(
                serviceType = SendEmailForgotMyPasswordApi::class.java,
                Constant.URL,
                headers = provideHeadersForgotMyPassword()
            )
        )
    }
}
