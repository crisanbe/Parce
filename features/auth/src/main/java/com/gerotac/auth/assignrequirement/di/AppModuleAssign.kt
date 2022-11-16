package com.gerotac.auth.assignrequirement.di

import com.gerotac.auth.assignrequirement.data.remote.api.AssignRequirementApi
import com.gerotac.auth.codeverificationRegister.di.ServiceBuilderGeneral
import com.gerotac.auth.requirement.data.remote.api.RequirementApi
import com.gerotac.auth.assignrequirement.data.repository.assignrepository.AssignTeacherRepositoryImpl
import com.gerotac.auth.assignrequirement.domain.repository.AssignTeacherRepository
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
        fun provideAssignTeacherRepository(): AssignTeacherRepository {
            return AssignTeacherRepositoryImpl(
                ServiceBuilderGeneral.ServiceBuilderRetrofit
                    .buildService(AssignRequirementApi::class.java)
            )
    }
}