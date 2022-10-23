package com.parce.auth.profileUser.di

import com.parce.auth.codeverificationRegister.di.ServiceBuilderGeneral
import com.parce.auth.newcode.data.remote.NewCodeApi
import com.parce.auth.newcode.data.repository.NewCodeRepositoryImpl
import com.parce.auth.newcode.domain.repository.NewCodeRepository
import com.parce.auth.profileUser.data.remote.api.GetProfileUserApi
import com.parce.auth.profileUser.data.repository.ProfileUserRepositoryImpl
import com.parce.auth.profileUser.domain.repository.ProfileUserRepository
import com.parce.shared.commons.Constant
import dagger.Binds
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