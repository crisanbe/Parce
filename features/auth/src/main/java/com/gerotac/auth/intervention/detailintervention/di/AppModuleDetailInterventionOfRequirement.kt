package com.gerotac.auth.intervention.detailintervention.di

import com.gerotac.auth.intervention.detailintervention.data.remote.api.GetDetailInterventionApi
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
object AppModuleDetailInterventionOfRequirement {

    @Provides
    @Singleton
    fun provideDetailInterventionRepository(): GetDetailInterventionApi {
        return Retrofit.Builder()
            .baseUrl(Constant.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetDetailInterventionApi::class.java)
    }
}