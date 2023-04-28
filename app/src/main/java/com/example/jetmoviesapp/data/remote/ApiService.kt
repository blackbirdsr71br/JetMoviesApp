package com.example.jetmoviesapp.data.remote

import com.example.jetmoviesapp.common.Constants
import com.example.jetmoviesapp.data.remote.genre.GenreResponse
import com.example.jetmoviesapp.data.remote.latest.LatestResponse
import com.example.jetmoviesapp.data.remote.movie.MovieResponse
import com.example.jetmoviesapp.data.remote.movie_detail.MovieDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.TOP_RATED)
    suspend fun getTopRatedMovies(
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int,
    ): MovieResponse

    @GET(Constants.GENRE_MOVIE)
    suspend fun getGenres(
        @Query("language") language: String = "es-ES",
    ): GenreResponse

    @GET(Constants.POPULAR)
    suspend fun getPopularMovies(
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int,
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "es-ES",
        @Query("append_to_response") appendToResponse: String = "credits,similar",
    ): MovieDetailDto

    @GET(Constants.DISCOVER)
    suspend fun getMovieWithGenres(
        @Query("language") language: String = "es-ES",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int,
        @Query("with_genres") genreId: Int,
    ): MovieResponse

    @GET(Constants.SEARCH_MOVIE)
    suspend fun searchMovie(
        @Query("language") language: String = "es-ES",
        @Query("query") query: String,
        @Query("page") page: Int,
    ): MovieResponse

    @GET(Constants.NOW_PLAYING)
    suspend fun getNowPlayingMovies(
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int,
    ): MovieResponse

    @GET(Constants.LATEST)
    suspend fun getLatestMovies(
        @Query("language") language: String = "es-ES",
        @Query("page") page: Int,
    ): MovieResponse
}
