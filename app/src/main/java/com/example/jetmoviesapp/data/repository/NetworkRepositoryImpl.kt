package com.example.jetmoviesapp.data.repository

import com.example.jetmoviesapp.common.Resource
import com.example.jetmoviesapp.data.remote.ApiService
import com.example.jetmoviesapp.data.remote.genre.GenreResponse
import com.example.jetmoviesapp.data.remote.movie.MovieResponse
import com.example.jetmoviesapp.data.remote.moviedetail.toMovieDetail
import com.example.jetmoviesapp.domain.model.HomeType
import com.example.jetmoviesapp.domain.model.MovieDetail
import com.example.jetmoviesapp.domain.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val api: ApiService
) : NetworkRepository {

    override suspend fun getTopRatedMovies(page: Int): MovieResponse {
        return api.getTopRatedMovies(page = page)
    }

    override suspend fun getNowPlayingMovies(page: Int): MovieResponse {
        return api.getNowPlayingMovies(page = page)
    }

    override suspend fun getLatestMovies(page: Int): MovieResponse {
        return api.getLatestMovies(page = page)
    }

    override suspend fun getGenres(): GenreResponse {
        return api.getGenres()
    }

    override suspend fun getPopularMovies(page: Int): MovieResponse {
        return api.getPopularMovies(page = page)
    }

    override suspend fun getMovieWithGenres(page: Int, genreId: Int): MovieResponse {
        return api.getMovieWithGenres(page = page, genreId = genreId)
    }

    override suspend fun searchMovie(page: Int, query: String): MovieResponse {
        return api.searchMovie(query = query, page = page)
    }

    override fun getMovieByIdN(id: Int): Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading())
        try {
            val movie = api.getMovieDetail(movieId = id).toMovieDetail()
            emit(Resource.Success(movie))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getHomeMovies(): Flow<Resource<List<HomeType>>> = flow {
        emit(Resource.Loading())
        try {
            val popular = api.getPopularMovies(page = 1).results
            val topRated = api.getTopRatedMovies(page = 1).results

            val list = listOf(HomeType.TopRated(topRated), HomeType.Popular(popular))

            emit(Resource.Success(list))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}
