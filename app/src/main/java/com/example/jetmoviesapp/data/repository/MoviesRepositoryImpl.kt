package com.example.jetmoviesapp.data.repository

import com.example.jetmoviesapp.data.local.WatchListDao
import com.example.jetmoviesapp.data.local.entities.MovieEntity
import com.example.jetmoviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val dao: WatchListDao,
) : MoviesRepository {

    override suspend fun insert(movie: MovieEntity) {
        dao.insert(movie = movie)
    }

    override suspend fun delete(movie: MovieEntity) {
        dao.delete(movie = movie)
    }

    override fun getWatchList(): Flow<List<MovieEntity>> = dao.getWatchList().flowOn(Dispatchers.IO)

    override suspend fun getMovieById(id: Int): MovieEntity? {
        return dao.getMovieByIdFromLocal(id = id)
    }
}
