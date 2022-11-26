package com.gerotac.auth.intervention.getintervention.di

import com.gerotac.auth.intervention.getintervention.data.repository.DetailRequirementInterventionRepositoryImpl
import com.gerotac.auth.intervention.getintervention.domain.repository.DetailRequirementInterventionRepository
import com.gerotac.auth.requirement.data.repository.GetDetailRequirementRepositoryImpl
import com.gerotac.auth.requirement.data.repository.GetRequirementRepositoryImpl
import com.gerotac.auth.requirement.data.repository.RequirementRepositoryImpl
import com.gerotac.auth.requirement.domain.repository.DetailRequirementRepository
import com.gerotac.auth.requirement.domain.repository.GetRequirementRepository
import com.gerotac.auth.requirement.domain.repository.RequirementRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesListInterventionDetailModule {

    @Binds
    abstract fun bindGetDetailOfInterventionRequirementRepository(impl: DetailRequirementInterventionRepositoryImpl):
            DetailRequirementInterventionRepository
}