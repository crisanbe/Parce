package com.gerotac.auth.requirement.di

import com.gerotac.auth.codeverificationRegister.di.ServiceBuilderGeneral
import com.gerotac.auth.requirement.data.remote.api.RequirementApi
import com.gerotac.auth.requirement.data.repository.DeleteRequirementRepositoryImpl
import com.gerotac.auth.requirement.domain.repository.DeleteRequirementRepository
import com.gerotac.auth.updaterequirement.data.remote.UpdateRequirementApi
import com.gerotac.auth.updaterequirement.data.repository.UpdateRequirementRepositoryImpl
import com.gerotac.auth.updaterequirement.domain.repository.UpdateRequirementRepository
import com.gerotac.shared.commons.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleRequirement {

    @Provides
    @Singleton
    fun provideRequirementRepository(): RequirementApi {
        return Retrofit.Builder()
            .baseUrl(Constant.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RequirementApi::class.java)
    }
}