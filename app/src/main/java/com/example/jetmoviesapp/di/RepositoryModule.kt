package com.example.jetmoviesapp.di

import com.example.local.domain.repository.MoviesRepository
import com.example.local.domain.repository.MoviesRepositoryImpl
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
    abstract fun bindRepository(repositoryImpl: com.example.remote.domain.repository.NetworkRepositoryImpl): com.example.remote.domain.repository.NetworkRepository

    @Binds
    @Singleton
    abstract fun bindMoviesRepository(localRepositoryImpl: MoviesRepositoryImpl): MoviesRepository
}
