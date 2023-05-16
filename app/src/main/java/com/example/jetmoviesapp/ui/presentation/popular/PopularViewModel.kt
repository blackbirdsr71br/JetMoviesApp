package com.example.jetmoviesapp.ui.presentation.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.remote.data.paging.MoviePagingSource
import com.example.remote.domain.usecases.networkUseCases.UseCaseNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val useCaseNetwork : UseCaseNetwork,
) : ViewModel() {
    val popularMovies = Pager(
        config = PagingConfig(pageSize = 20)
    ) {
        MoviePagingSource(
            useCaseNetwork,
            source = MoviePagingSource.Source.Popular
        )
    }.flow
        .cachedIn(viewModelScope)
        .flowOn(Dispatchers.IO)
}
