package com.example.jetmoviesapp.ui.presentation.toprated

import com.example.jetmoviesapp.data.paging.SearchMovie
import com.example.jetmoviesapp.domain.repository.NetworkRepository
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetByGenderMoviesUseCase
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetGenresMoviesUseCase
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetHomeMoviesUseCase
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetLatestMoviesUseCase
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetMovieByIdNetworkUseCase
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetNowPlayMoviesUseCase
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetPopularMoviesUseCase
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetTopRatedMoviesUseCase
import com.example.jetmoviesapp.domain.usecases.networkUseCases.UseCaseNetwork
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TopRatedViewModelTest {
    private lateinit var networkRepository: NetworkRepository

    private lateinit var viewModel: TopRatedViewModel
    lateinit var useCaseNetwork: UseCaseNetwork
    private val getTopRatedMovies = GetTopRatedMoviesUseCase(networkRepository)
    private val getGenresMovies = GetGenresMoviesUseCase(networkRepository)
    private val getLatestMovies = GetLatestMoviesUseCase(networkRepository)
    private val getMovieById = GetMovieByIdNetworkUseCase(networkRepository)
    private val getMovieByGenresMovies = GetByGenderMoviesUseCase(networkRepository)
    private val getMoviesHome = GetHomeMoviesUseCase(networkRepository)
    private val getNowPlayMovies = GetNowPlayMoviesUseCase(networkRepository)
    private val getPopularMovies = GetPopularMoviesUseCase(networkRepository)
    private val searchMovie = SearchMovie(networkRepository)

    @Before
    fun setUp() {
        useCaseNetwork = UseCaseNetwork(
            getGenresMovies = getGenresMovies,
            getTopRatedMovies = getTopRatedMovies,
            getLatestMovies = getLatestMovies,
            getMoviebyId = getMovieById,
            getMoviesByGenderMovies = getMovieByGenresMovies,
            getMoviesHome = getMoviesHome,
            getNowPlayMovies = getNowPlayMovies,
            getPopularMovies = getPopularMovies,
            searchMovie = searchMovie
        )

        viewModel = TopRatedViewModel(useCaseNetwork)
    }

    @Test
    fun getTopRatedTest() = runBlocking {
        val viewModellist = viewModel.topRated.toList()
        assert(viewModellist.isEmpty())
    }
}
