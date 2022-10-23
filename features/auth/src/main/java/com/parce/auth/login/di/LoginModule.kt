package com.parce.auth.login.di

import com.parce.auth.login.data.remote.LoginApi
import com.parce.auth.login.data.repository.LoginRepositoryImpl
import com.parce.auth.login.domain.repository.LoginRepository
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
object LoginModule {
    @Provides
    @Singleton
    fun provideHeadersLogin(): Map<String, String> = Header.headers

    @Provides
    @Singleton
    fun provideLoginRepository(): LoginRepository {
        return LoginRepositoryImpl(
            ServiceBuilder.createService(
                serviceType = LoginApi::class.java,
                Constant.URL,
                headers = provideHeadersLogin()
            )
        )
    }
}