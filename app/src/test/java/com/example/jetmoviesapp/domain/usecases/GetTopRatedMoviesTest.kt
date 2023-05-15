package com.example.jetmoviesapp.domain.usecases

import com.example.remote.data.remote.movie.Movie
import com.example.remote.data.remote.movie.MovieResponse
import com.example.remote.domain.repository.NetworkRepository
import com.example.remote.domain.usecases.networkUseCases.GetTopRatedMoviesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class GetTopRatedMoviesTest {

    @RelaxedMockK
    private lateinit var repository: com.example.remote.domain.repository.NetworkRepository

    lateinit var getTopRatedMovies: GetTopRatedMoviesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getTopRatedMovies = GetTopRatedMoviesUseCase(repository)
    }

    @Test
    fun `Get Top Rated Movies from api`() = runBlocking {
        // Given

        val movieList = listOf(
            com.example.remote.data.remote.movie.Movie(
                posterPath = "lugar del poster",
                adult = false,
                overview = "Pelicula sobre los test",
                releaseDate = LocalDateTime.now().toString(),
                genreIds = listOf(100, 1000, 3000),
                id = 1,
                originalTitle = "Unis Test en Android",
                originalLanguage = "Español",
                title = "Los Unittest",
                backdropPath = null,
                popularity = 80.0,
                voteCount = 100,
                video = false,
                voteAverage = 101.0
            )
        )
        val movieResponse = com.example.remote.data.remote.movie.MovieResponse(
            page = 1,
            results = movieList,
            total_pages = 1,
            total_results = 1
        )

        coEvery { repository.getTopRatedMovies(page = 1) } returns movieResponse

        // When
        val response = getTopRatedMovies(page = 1)

        // Then

        coVerify(exactly = 1) { repository.getTopRatedMovies(page = 1) }

        assert(movieResponse == response)
    }
}
