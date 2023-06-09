package com.example.jetmoviesapp.ui.presentation.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.jetmoviesapp.data.paging.MoviePagingSource
import com.example.jetmoviesapp.domain.usecases.UseCaseNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val networkRepository: UseCaseNetwork,
) : ViewModel() {
    val popularMovies = Pager(
        config = PagingConfig(pageSize = 20),
    ) {
        MoviePagingSource(
             networkRepository,
            source = MoviePagingSource.Source.Popular,
        )
    }.flow.cachedIn(viewModelScope)
}
