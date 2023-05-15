package com.example.jetmoviesapp.di

import com.example.jetmoviesapp.data.repository.GenresRepositoryImpl
import com.example.local.domain.repository.MoviesRepositoryImpl
import com.example.jetmoviesapp.data.repository.NetworkRepositoryImpl
import com.example.jetmoviesapp.domain.repository.GenresRepository
import com.example.local.domain.repository.MoviesRepository
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
    abstract fun bindMoviesRepository(localRepositoryImpl: MoviesRepositoryImpl): MoviesRepository

    @Binds
    @Singleton
    abstract fun bindGeresRepository(localRepositoryImpl: GenresRepositoryImpl): GenresRepository
}
