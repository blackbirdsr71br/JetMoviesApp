package com.example.jetmoviesapp.ui.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetmoviesapp.domain.model.HomeType
import com.example.jetmoviesapp.ui.presentation.genres.GenresViewModel
import com.example.jetmoviesapp.ui.presentation.home.components.Header
import com.example.jetmoviesapp.ui.presentation.home.components.PopularHomeItem
import com.example.jetmoviesapp.ui.presentation.home.components.TopRatedHomeItem
import com.example.jetmoviesapp.ui.presentation.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    genresViewModel: GenresViewModel = hiltViewModel(),
    navController: NavController,
) {
    val genres = genresViewModel.stateGenres.value
    val state = viewModel.state.value
    val homeItems = state.homeList
    /*Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "JetMovies",
                        modifier = Modifier.graphicsLayer(alpha = 0.99f)
                            .drawWithCache {
                                val brush = Brush.horizontalGradient(RainbowColors)
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(brush, blendMode = BlendMode.SrcAtop)
                                }
                            },
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                    )
                },
                modifier = Modifier
                    .padding(top = 30.dp)
                    .height(50.dp),
            )
        },
        drawerShape = RoundedCornerShape(0.dp),
        drawerContent = {
            DrawerCompose(
                scope = scope,
                scaffoldState = scaffoldState,
                userName = "Alex",
                userId = "1046589",
            ) { }
        },
    ) {*/
    Column(
        modifier = Modifier
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            homeItems.forEach { homeType ->
                when (homeType) {
                    is HomeType.TopRated -> {
                        item {
                            Header(header = "Top Rated", onClickSeeMore = {
                                navController.navigate(Screen.TopRated.route)
                            })
                        }
                        item {
                            LazyRow {
                                items(homeType.topRated) { topRated ->
                                    TopRatedHomeItem(topRated = topRated) { navigatedItem ->
                                        navController.navigate(Screen.MovieDetail.route + "/${navigatedItem.id}")
                                    }
                                }
                            }
                        }
                    }

                    is HomeType.Popular -> {
                        item {
                            Header(header = "Popular", onClickSeeMore = {
                                navController.navigate(Screen.Popular.route)
                            })
                        }
                        items(items = homeType.popular) { popular ->
                            PopularHomeItem(
                                popular = popular,
                                genres = genres,
                            ) { navigatedItem ->
                                navController.navigate(Screen.MovieDetail.route + "/${navigatedItem.id}")
                            }
                        }
                    }
                }
            }
        }
        if (state.error.isNotBlank()) {
            Text(text = "${state.error} ds")
        }
        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
    // }
}
