package com.example.jetmoviesapp.ui.presentation.latest

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetmoviesapp.data.paging.MoviePagingSource
import com.example.jetmoviesapp.data.remote.latest.Latest
import com.example.jetmoviesapp.data.remote.movie.Movie
import com.example.jetmoviesapp.domain.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestMoviesViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
) : ViewModel() {

    val latest: Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            MoviePagingSource(
                networkRepository,
                MoviePagingSource.Source.Latest,
            )
        },
    ).flow.cachedIn(viewModelScope)
}
