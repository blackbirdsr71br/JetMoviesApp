package com.example.jetmoviesapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetmoviesapp.data.remote.movie.Movie
import com.example.jetmoviesapp.domain.repository.NetworkRepository
import retrofit2.HttpException
import java.io.IOException

class SearchPagingSource(
    private val networkRepository: NetworkRepository,
    private val query: String
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val currentPage = params.key ?: 1
        return try {
            val response = networkRepository.searchMovie(page = currentPage, query = query)
            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = response.page.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}