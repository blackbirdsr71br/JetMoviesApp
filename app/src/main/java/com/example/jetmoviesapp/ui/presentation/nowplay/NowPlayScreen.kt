package com.example.jetmoviesapp.ui.presentation.nowplay

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.jetmoviesapp.R
import com.example.jetmoviesapp.ui.presentation.composables.JetMoviesTopBar
import com.example.jetmoviesapp.ui.presentation.composables.MovieItem
import com.example.jetmoviesapp.ui.presentation.navigation.Screen

@Composable
fun NowPlayScreen(
    viewModel: NowPlayViewModel = hiltViewModel(),
    navController: NavController
) {
    val nowPlayList = viewModel.nowPlay.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            JetMoviesTopBar(
                title = "Now Playing",
                backGroundColor = Color.Transparent,
                navController = navController
            )
        },
        modifier = Modifier.statusBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = it)
        ) {
            items(
                count = nowPlayList.itemCount,
                key = nowPlayList.itemKey(),
                contentType = nowPlayList.itemContentType()
            ) { index ->
                val item = nowPlayList[index]
                item?.let { topRated ->
                    MovieItem(movie = topRated) { navigatedItem ->
                        navController.navigate(route = Screen.MovieDetail.route + "/${navigatedItem.id}")
                    }
                }
            }
            nowPlayList.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        // when first time response page is loading
                        item { CircularProgressIndicator(color = Color.DarkGray) }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            LinearProgressIndicator(
                                color = Color.Red,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .statusBarsPadding()
                            )
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item { Text(text = "Error: " + stringResource(R.string.app_error)) }
                    }
                }
            }
        }
    }
}
