package com.example.jetmoviesapp.di

import com.example.jetmoviesapp.data.repository.LocalRepositoryImpl
import com.example.jetmoviesapp.data.repository.NetworkRepositoryImpl
import com.example.jetmoviesapp.domain.repository.LocalRepository
import com.example.jetmoviesapp.domain.repository.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: NetworkRepositoryImpl): NetworkRepository

    @Binds
    @Singleton
    abstract fun bindLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository

}