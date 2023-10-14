package com.gerotac.auth.reports.di

import com.gerotac.auth.reports.data.repository.ReportMessageRepositoryImpl
import com.gerotac.auth.reports.domain.repository.ReportMessageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesReportResponseModule {
    @Binds
    abstract fun bindReportResponseRepository(impl: ReportMessageRepositoryImpl):
            ReportMessageRepository
}