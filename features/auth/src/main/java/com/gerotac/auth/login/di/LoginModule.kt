package com.gerotac.auth.login.di

import com.gerotac.auth.login.data.remote.LoginApi
import com.gerotac.auth.login.data.repository.LoginRepositoryImpl
import com.gerotac.auth.login.domain.repository.LoginRepository
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