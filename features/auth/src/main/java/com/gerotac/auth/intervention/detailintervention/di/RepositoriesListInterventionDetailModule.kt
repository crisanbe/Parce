package com.gerotac.auth.intervention.detailintervention.di

import com.gerotac.auth.intervention.detailintervention.data.repository.DetailInterventionRepositoryImpl
import com.gerotac.auth.intervention.detailintervention.domain.repository.DetailInterventionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesListInterventionDetailModule {

    @Binds
    abstract fun bindGetDetailOfInterventionRequirementRepository(impl: DetailInterventionRepositoryImpl):
            DetailInterventionRepository
}