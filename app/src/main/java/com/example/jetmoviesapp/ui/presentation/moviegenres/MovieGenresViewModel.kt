package com.example.jetmoviesapp.ui.presentation.moviegenres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetmoviesapp.data.paging.MoviePagingSource
import com.example.remote.data.remote.movie.Movie
import com.example.jetmoviesapp.domain.usecases.remote.networkUseCases.UseCaseNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieGenresViewModel @Inject constructor(
    private val useCaseNetwork: UseCaseNetwork
) : ViewModel() {

    fun moviesWithGenres(genreId: Int): Flow<PagingData<com.example.remote.data.remote.movie.Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                MoviePagingSource(
                    useCaseNetwork,
                    MoviePagingSource.Source.MovieWithGenres(genreId = genreId)
                )
            }
        ).flow.cachedIn(viewModelScope)
    }
}
