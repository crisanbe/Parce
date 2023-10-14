package com.gerotac.auth.profileUser.di

import com.gerotac.auth.profileUser.data.remote.api.GetProfileUserApi
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
object AppModuleGetProfile {
    @Provides
    @Singleton
    fun providerGetProfileRepository(): GetProfileUserApi {
        return Retrofit.Builder()
            .baseUrl(Constant.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GetProfileUserApi::class.java)
    }
}