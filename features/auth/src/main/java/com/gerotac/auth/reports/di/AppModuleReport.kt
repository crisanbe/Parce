package com.gerotac.auth.reports.di

import com.gerotac.auth.reports.data.api.ReportResponseApi
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
object AppModuleReport {
    @Provides
    @Singleton
    fun provideReportResponseRepository(): ReportResponseApi {
        return Retrofit.Builder()
            .baseUrl(Constant.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReportResponseApi::class.java)
    }
}