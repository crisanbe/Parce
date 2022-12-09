package com.gerotac.auth.reports.di

import com.gerotac.auth.reports.data.repository.ReportRepositoryImpl
import com.gerotac.auth.reports.domain.repository.ReportRepository
import com.gerotac.auth.requirement.data.repository.DeleteRequirementRepositoryImpl
import com.gerotac.auth.requirement.data.repository.GetDetailRequirementRepositoryImpl
import com.gerotac.auth.requirement.data.repository.GetRequirementRepositoryImpl
import com.gerotac.auth.requirement.data.repository.RequirementRepositoryImpl
import com.gerotac.auth.requirement.domain.repository.DeleteRequirementRepository
import com.gerotac.auth.requirement.domain.repository.DetailRequirementRepository
import com.gerotac.auth.requirement.domain.repository.GetRequirementRepository
import com.gerotac.auth.requirement.domain.repository.RequirementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesReportModule {

    @Binds
    abstract fun bindReportRepository(impl: ReportRepositoryImpl):
            ReportRepository
}