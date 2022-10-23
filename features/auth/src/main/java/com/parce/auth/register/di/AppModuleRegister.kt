package com.parce.auth.register.di

import android.app.Application
import androidx.room.Room
import com.parce.auth.register.data.local.BdDataTokenRegister
import com.parce.auth.register.data.local.DaoTokenRegister
import com.parce.auth.register.data.remote.RegisterApi
import com.parce.auth.register.data.repository.RegisterRepositoryImpl
import com.parce.auth.register.domain.repository.RegisterRepository
import com.parce.auth.register.domain.repository.TokenRegisterUseCases
import com.parce.auth.register.domain.usecase.RegisterUseCase
import com.parce.network.ServiceBuilder
import com.parce.shared.commons.Constant
import com.parce.shared.commons.Header
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
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
