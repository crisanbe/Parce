package com.parce.auth.requirement.di

import com.parce.auth.requirement.data.remote.api.RequirementApi
import com.parce.shared.commons.Constant
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