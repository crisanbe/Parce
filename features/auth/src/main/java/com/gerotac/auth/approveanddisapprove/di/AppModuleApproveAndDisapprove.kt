package com.gerotac.auth.approveanddisapprove.di

import com.gerotac.auth.approveanddisapprove.data.remote.api.ApproveInterventionApi
import com.gerotac.auth.approveanddisapprove.data.repository.ApproveInterventionRepositoryImpl
import com.gerotac.auth.approveanddisapprove.domain.repository.ApproveInterventionRepository
import com.gerotac.auth.codeverificationRegister.di.ServiceBuilderGeneral
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleApproveAndDisapprove {
    @Provides
    @Singleton
    fun provideApproveInterventionRepository(): ApproveInterventionRepository {
        return ApproveInterventionRepositoryImpl(
            ServiceBuilderGeneral.ServiceBuilderRetrofit
                .buildService(ApproveInterventionApi::class.java)
        )
    }
}
