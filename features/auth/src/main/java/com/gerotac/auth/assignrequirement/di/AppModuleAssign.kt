package com.gerotac.auth.assignrequirement.di

import com.gerotac.auth.assignrequirement.data.remote.api.AssignRequirementApi
import com.gerotac.auth.assignrequirement.data.repository.assignrepository.AssignRepositoryImpl
import com.gerotac.auth.assignrequirement.domain.repository.AssignRepository
import com.gerotac.auth.codeverificationRegister.di.ServiceBuilderGeneral
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleAssign {
        @Provides
        @Singleton
        fun provideAssignTeacherRepository(): AssignRepository {
            return AssignRepositoryImpl(
                ServiceBuilderGeneral.ServiceBuilderRetrofit
                    .buildService(AssignRequirementApi::class.java)
            )
    }
}