package com.example.jetmoviesapp.data.repository

import com.example.jetmoviesapp.data.local.GenresListDao
import com.example.jetmoviesapp.data.local.WatchListDao
import com.example.jetmoviesapp.data.local.entities.GenresEntity
import com.example.jetmoviesapp.data.local.entities.MovieEntity
import com.example.jetmoviesapp.domain.repository.GenresRepository
import com.example.jetmoviesapp.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenresRepositoryImpl @Inject constructor(
    private val dao: GenresListDao,
) : GenresRepository {

    override suspend fun insert(genre: GenresEntity) {
        dao.insert(genre = genre)
    }

    override suspend fun delete(genre: GenresEntity) {
        dao.delete(genre = genre)
    }
    override fun getGenresList(): Flow<List<GenresEntity>> = dao.getGenres().flowOn(Dispatchers.IO)
}
