package com.example.jetmoviesapp.ui.presentation.toprated

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
import com.example.remote.data.remote.movie.Movie

@Composable
fun TopRatedScreen(
    viewModel: TopRatedViewModel = hiltViewModel(),
    navController: NavController
) {
    val topRatedList = viewModel.topRated.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            JetMoviesTopBar(
                title = "Top Rated",
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
                count = topRatedList.itemCount,
                key = topRatedList.itemKey(),
                contentType = topRatedList.itemContentType()
            ) { index ->
                val item = topRatedList[index]
                item?.let { topRated ->
                    MovieItem(movie = topRated) { navigatedItem ->
                        navController.navigate(route = Screen.MovieDetail.route + "/${navigatedItem.id}")
                    }
                }
            }
            topRatedList.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        // when first time response page is loading
                        item {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator(color = Color.DarkGray)
                                Text(text = "Cargado información de Peliculas...")
                            }
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            Column() {
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
                        item { Text(text = "Error: " + stringResource(R.string.app_error)) }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShowTopRatedItem(){
    MovieItem(
        movie = Movie(
            posterPath = "https://api.themoviedb.org/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
            adult = false,
            overview = "Pelicula",
            releaseDate = "01/01/2021",
            genreIds = listOf(1000, 2000, 4000),
            id = 10,
            originalTitle = "Pelcula de Prueba",
            originalLanguage = "Español",
            title = "Pelicula de Prueba",
            backdropPath = null,
            popularity = 80.2,
            voteCount = 8,
            video = false,
            voteAverage = 90.0
        ),
        onClick = {}
    )
}
