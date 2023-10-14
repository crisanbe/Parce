package com.gerotac.auth.dropdownapi.dropacademicprograms.di

import com.gerotac.auth.dropdownapi.dropacademicprograms.data.remote.api.GetApiDropDown
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
object AppModuleGetAcademicPrograms {
    @Provides
    @Singleton
    fun providerGetAcademicProgramsRepository(): GetApiDropDown {
        return Retrofit.Builder()
            .baseUrl(Constant.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetApiDropDown::class.java)
    }
}