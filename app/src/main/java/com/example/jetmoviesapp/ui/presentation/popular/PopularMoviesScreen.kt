package com.example.jetmoviesapp.ui.presentation.popular

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.jetmoviesapp.ui.presentation.composables.JetMoviesTopBar
import com.example.jetmoviesapp.ui.presentation.composables.MovieItem
import com.example.jetmoviesapp.ui.presentation.navigation.Screen


@Composable
fun PopularMoviesScreen(
    viewModel: PopularViewModel = hiltViewModel(),
    navController: NavController
) {
    val popularMovies = viewModel.popularMovies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            JetMoviesTopBar(
                title = "Popular",
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
                count = popularMovies.itemCount,
                key = popularMovies.itemKey(),
                contentType = popularMovies.itemContentType()
            ) { index ->
                val item = popularMovies[index]
                item?.let {
                    MovieItem(movie = item) { navigated ->
                        navController.navigate(route = Screen.MovieDetail.route + "/${navigated.id}") {
                            navController.popBackStack(
                                route = Screen.MovieDetail.route,
                                inclusive = true
                            )
                            navController.popBackStack(
                                route = Screen.Popular.route,
                                inclusive = true
                            )
                        }
                    }
                }
            }
            popularMovies.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                CircularProgressIndicator(color = Color.DarkGray)
                                Text(text = "Cargado información de Peliculas...")
                            }
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                LinearProgressIndicator(
                                    color = Color.Red,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .statusBarsPadding()
                                )
                                Text(text = "Cargado información de Peliculas...")
                            }
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item {
                            Text(text = "Error: !")
                        }
                    }
                }
            }
        }
    }
}
