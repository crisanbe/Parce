package com.parce.auth.profileUser.di

import com.parce.auth.profileUser.data.repository.ProfileUserRepositoryImpl
import com.parce.auth.profileUser.domain.repository.ProfileUserRepository
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