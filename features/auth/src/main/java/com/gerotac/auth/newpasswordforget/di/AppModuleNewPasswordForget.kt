package com.gerotac.auth.newpasswordforget.di

import com.gerotac.auth.newpasswordforget.data.remote.NewPasswordForgetApi
import com.gerotac.auth.newpasswordforget.data.repository.NewPasswordForgetRepositoryImpl
import com.gerotac.auth.newpasswordforget.domain.repository.NewPasswordForgetRepository
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
object AppModuleNewPasswordForget {
    @Provides
    @Singleton
    fun providerHeadersNewPasswordForget(): Map<String, String> = Header.headers
    @Provides
    @Singleton
    fun providerNewPasswordForgetRepository(): NewPasswordForgetRepository {
        return NewPasswordForgetRepositoryImpl(
            ServiceBuilder.createService(
                serviceType = NewPasswordForgetApi::class.java,
                Constant.URL,
                headers = providerHeadersNewPasswordForget()
            )
        )
    }
}