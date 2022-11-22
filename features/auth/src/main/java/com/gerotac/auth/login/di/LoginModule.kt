package com.gerotac.auth.login.di

import com.gerotac.auth.login.data.remote.LoginApi
import com.gerotac.auth.login.data.repository.LoginRepositoryImpl
import com.gerotac.auth.login.domain.repository.LoginRepository
import com.gerotac.network.ServiceBuilder
import com.gerotac.shared.commons.Constant
import com.gerotac.shared.commons.Header
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    fun getClient(): OkHttpClient =
        OkHttpClient.Builder()
            .protocols(listOf(Protocol.HTTP_1_1))
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(
                HeaderInterceptor()
            )
            .build()

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
    }

    @Provides
    fun provideLoginServiceRemote(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginRepositoryImp(
        api: LoginApi,
    ): LoginRepository {
        return LoginRepositoryImpl(
            api = api
        )
    }

}