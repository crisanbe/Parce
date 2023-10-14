package com.gerotac.auth.newcode.di

import com.gerotac.auth.codeverificationRegister.di.ServiceBuilderGeneral
import com.gerotac.auth.newcode.data.remote.NewCodeApi
import com.gerotac.auth.newcode.data.repository.NewCodeRepositoryImpl
import com.gerotac.auth.newcode.domain.repository.NewCodeRepository
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