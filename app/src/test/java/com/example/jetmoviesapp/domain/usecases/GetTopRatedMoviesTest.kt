package com.example.jetmoviesapp.domain.usecases

import com.example.jetmoviesapp.data.remote.movie.Movie
import com.example.jetmoviesapp.data.remote.movie.MovieResponse
import com.example.jetmoviesapp.domain.repository.NetworkRepository
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
    private lateinit var repository: NetworkRepository
    lateinit var getTopRatedMovies: GetTopRatedMovies

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getTopRatedMovies = GetTopRatedMovies(repository)
    }

    @Test
    fun `Get Top Rated Movies from api`() = runBlocking {
        // Given

        val movieList = listOf(
            Movie(
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
                voteAverage = 101.0,
            ),
        )
        val movieResponse = MovieResponse(
            page = 1,
            results = movieList,
            total_pages = 1,
            total_results = 1,
        )

        coEvery { repository.getTopRatedMovies(page = 1) } returns movieResponse

        // When
        val respuesta = getTopRatedMovies(page = 1)

        // Then

        coVerify(exactly = 1) { repository.getTopRatedMovies(page = 1) }
    }
}
