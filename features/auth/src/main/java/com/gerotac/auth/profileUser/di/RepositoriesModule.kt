package com.gerotac.auth.profileUser.di

import com.gerotac.auth.profileUser.data.repository.ProfileUserRepositoryImpl
import com.gerotac.auth.profileUser.domain.repository.ProfileUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindUserRepository(impl: ProfileUserRepositoryImpl): ProfileUserRepository
}