package com.parce.auth.requirement.di

import com.parce.auth.requirement.data.repository.GetDetailRequirementRepositoryImpl
import com.parce.auth.requirement.data.repository.GetRequirementRepositoryImpl
import com.parce.auth.requirement.data.repository.RequirementRepositoryImpl
import com.parce.auth.requirement.domain.repository.DetailRequirementRepository
import com.parce.auth.requirement.domain.repository.GetRequirementRepository
import com.parce.auth.requirement.domain.repository.RequirementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindRequirementRepository(impl: RequirementRepositoryImpl):
            RequirementRepository

    @Binds
    abstract fun bindGetRequirementRepository(impl: GetRequirementRepositoryImpl):
            GetRequirementRepository

    @Binds
    abstract fun bindGetDetailRequirementRepository(impl: GetDetailRequirementRepositoryImpl):
            DetailRequirementRepository
}