package com.gerotac.auth.newcodeverifyemail.di

import com.gerotac.auth.codeverificationRegister.di.ServiceBuilderGeneral
import com.gerotac.auth.newcodeverifyemail.data.remote.NewCodeApi
import com.gerotac.auth.newcodeverifyemail.data.repository.NewCodeRepositoryImpl
import com.gerotac.auth.newcodeverifyemail.domain.repository.NewCodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleNewCode {
    @Provides
    @Singleton
    fun providerNewCodeRepository(): NewCodeRepository {
        return NewCodeRepositoryImpl(
            ServiceBuilderGeneral.ServiceBuilderRetrofit
                .buildService(NewCodeApi::class.java)
        )
    }
}