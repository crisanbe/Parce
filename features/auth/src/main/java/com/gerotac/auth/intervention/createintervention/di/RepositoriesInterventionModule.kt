package com.gerotac.auth.intervention.createintervention.di

import com.gerotac.auth.intervention.createintervention.data.repository.SaveInterventionRepositoryImpl
import com.gerotac.auth.intervention.createintervention.domain.repository.SaveInterventionRepository
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
abstract class RepositoriesInterventionModule {

    @Binds
    abstract fun bindSaveInterventionRepository(impl: SaveInterventionRepositoryImpl):
            SaveInterventionRepository
}