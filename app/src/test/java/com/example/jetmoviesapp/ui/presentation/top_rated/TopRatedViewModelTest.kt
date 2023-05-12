package com.example.jetmoviesapp.ui.presentation.top_rated

import com.example.jetmoviesapp.data.paging.SearchMovie
import com.example.jetmoviesapp.domain.repository.NetworkRepository
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetByGenderMovies
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetGenresMovies
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetHomeMovies
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetLatestMovies
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetMovieByIdN
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetNowPlayMovies
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetPopularMovies
import com.example.jetmoviesapp.domain.usecases.networkUseCases.GetTopRatedMovies
import com.example.jetmoviesapp.domain.usecases.networkUseCases.UseCaseNetwork
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MainViewModelTest {
    private lateinit var networkRepository: NetworkRepository

    private lateinit var viewModel: TopRatedViewModel
    lateinit var useCaseNetwork: UseCaseNetwork
    val getTopRatedMovies = GetTopRatedMovies(networkRepository)
    val getGenresMovies = GetGenresMovies(networkRepository)
    val getLatestMovies = GetLatestMovies(networkRepository)
    val getMovieById = GetMovieByIdN(networkRepository)
    val getMovieByGenresMovies = GetByGenderMovies(networkRepository)
    val getMoviesHome = GetHomeMovies(networkRepository)
    val getNowPlayMovies = GetNowPlayMovies(networkRepository)
    val getPopularMovies = GetPopularMovies(networkRepository)
    val searchMovie = SearchMovie(networkRepository)

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
            searchMovie = searchMovie,
        )

        viewModel = TopRatedViewModel(useCaseNetwork)
    }

    @Test
    fun getTopRatedTest() = runBlocking {
        val viewModellist = viewModel.topRated.toList()
        assert(viewModellist.isNotEmpty())
    }
}
