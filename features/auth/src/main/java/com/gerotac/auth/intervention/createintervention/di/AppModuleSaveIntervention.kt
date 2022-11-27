package com.gerotac.auth.intervention.createintervention.di

import com.gerotac.auth.intervention.createintervention.data.remote.api.SaveInterventionApi
import com.gerotac.auth.requirement.data.remote.api.RequirementApi
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
object AppModuleSaveIntervention {

    @Provides
    @Singleton
    fun provideSaveInterventionRepository(): SaveInterventionApi {
        return Retrofit.Builder()
            .baseUrl(Constant.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SaveInterventionApi::class.java)
    }
}