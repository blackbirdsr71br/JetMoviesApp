package com.example.local.domain.usecases

import com.example.local.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesLocalModule {
    @Provides
    fun usecases(repository: MoviesRepository) = UseCaseMovie(
        InsertMovieUseCase(repository),
        DeleteMovieUseCase(repository),
        GetWatchListUseCase(repository),
        GetMovieByIdUseCase(repository)
    )
}

data class UseCaseMovie(
    val insert: InsertMovieUseCase,
    val deleteMovie: DeleteMovieUseCase,
    val getWatchList: GetWatchListUseCase,
    val getMoviebyId: GetMovieByIdUseCase
)
