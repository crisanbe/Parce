package com.gerotac.auth.register.di

import android.app.Application
import androidx.room.Room
import com.gerotac.auth.register.data.local.BdDataTokenRegister
import com.gerotac.auth.register.data.remote.RegisterApi
import com.gerotac.auth.register.data.repository.RegisterRepositoryImpl
import com.gerotac.auth.register.domain.repository.RegisterRepository
import com.gerotac.auth.register.domain.repository.TokenRegisterUseCases
import com.gerotac.auth.register.domain.usecase.RegisterUseCase
import com.gerotac.network.ServiceBuilder
import com.gerotac.shared.commons.Constant
import com.gerotac.shared.commons.Header
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModuleRegister {

    /*@Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenFoodApi(client: OkHttpClient): RegisterApi {
        return Retrofit.Builder()
            .baseUrl(Constant.URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }*/

    @Provides
    @Singleton
    fun provideTrackerDatabase(app: Application): BdDataTokenRegister {
        return Room.databaseBuilder(
            app,
            BdDataTokenRegister::class.java,
            "tokenUser_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideHeaders(): Map<String, String> = Header.headers

    @Provides
    @Singleton
    fun provideRegisterRepository(
        db: BdDataTokenRegister
    ): RegisterRepository {
        return RegisterRepositoryImpl(
            db.dao,
            ServiceBuilder.createService(
                serviceType = RegisterApi::class.java,
                Constant.URL,
                headers = provideHeaders()
            )
        )
    }

    @ViewModelScoped
    @Provides
    fun provideTokenRegisterUseCases(
        repository: RegisterRepository,
    ): TokenRegisterUseCases {
        return TokenRegisterUseCases(
            toke = RegisterUseCase(repository)
        )
    }

}
