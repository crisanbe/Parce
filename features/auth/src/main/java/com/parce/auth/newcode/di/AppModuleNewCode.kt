package com.parce.auth.newcode.di

import com.parce.auth.codeverificationRegister.di.ServiceBuilderGeneral
import com.parce.auth.newcode.data.remote.NewCodeApi
import com.parce.auth.newcode.data.repository.NewCodeRepositoryImpl
import com.parce.auth.newcode.domain.repository.NewCodeRepository
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