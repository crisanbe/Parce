package com.gerotac.auth.sendemailforgotmypassword.di

import com.gerotac.auth.sendemailforgotmypassword.data.remote.SendEmailForgotMyPasswordApi
import com.gerotac.auth.sendemailforgotmypassword.data.repository.SendEmailForgotMyPasswordRepositoryImpl
import com.gerotac.auth.sendemailforgotmypassword.domain.repository.SendEmailForgotMyPasswordRepository
import com.gerotac.network.ServiceBuilder
import com.gerotac.shared.commons.Constant
import com.gerotac.shared.commons.Header
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
