package com.gerotac.auth.dropdownapi.dropdown.di

import com.gerotac.auth.dropdownapi.dropdown.data.repository.ApisDropRepositoryImpl
import com.gerotac.auth.dropdownapi.dropdown.domain.repository.ApisDropRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesAcademicModule {

    @Binds
    abstract fun bindAcademicRepository(impl: ApisDropRepositoryImpl):
            ApisDropRepository
}