package com.gerotac.auth.updaterequirement.di

import com.gerotac.auth.codeverificationRegister.di.ServiceBuilderGeneral
import com.gerotac.auth.updaterequirement.data.remote.UpdateRequirementApi
import com.gerotac.auth.updaterequirement.data.repository.UpdateRequirementRepositoryImpl
import com.gerotac.auth.updaterequirement.domain.repository.UpdateRequirementRepository
import com.gerotac.auth.updateuser.data.remote.UpdateUserApi
import com.gerotac.auth.updateuser.data.repository.UpdateUserRepositoryImpl
import com.gerotac.auth.updateuser.domain.repository.UpdateUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleUpdateRequirement {
    @Provides
    @Singleton
    fun provideUpdateRequirementRepository(): UpdateRequirementRepository {
        return UpdateRequirementRepositoryImpl(
            ServiceBuilderGeneral.ServiceBuilderRetrofit
                .buildService(UpdateRequirementApi::class.java)
        )
    }
}
