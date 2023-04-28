package com.example.jetmoviesapp.ui.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetmoviesapp.ui.presentation.detail.MovieDetailScreen
import com.example.jetmoviesapp.ui.presentation.genres.GenresScreen
import com.example.jetmoviesapp.ui.presentation.home.HomeScreen
import com.example.jetmoviesapp.ui.presentation.latest.LatestScreen
import com.example.jetmoviesapp.ui.presentation.movie_genres.MovieWithGenres
import com.example.jetmoviesapp.ui.presentation.now_play.NowPlayScreen
import com.example.jetmoviesapp.ui.presentation.popular.PopularMoviesScreen
import com.example.jetmoviesapp.ui.presentation.top_rated.TopRatedScreen
import com.example.jetmoviesapp.ui.presentation.watch_list.WatchListScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalCoroutinesApi::class,
)
@Composable
fun NavigateScreens(
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Start.route,
        modifier = Modifier.padding(paddingValues),
    ) {
        composable(Screen.Start.route) {
            HomeScreen(
                navController = navController,
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
            )
        }
        composable(Screen.TopRated.route) {
            TopRatedScreen(navController = navController)
        }
        composable(Screen.Popular.route) {
            PopularMoviesScreen(navController = navController)
        }
        composable(Screen.MovieDetail.route + "/{movie_id}") {
            MovieDetailScreen(navController = navController)
        }
        composable(Screen.Genres.route) {
            GenresScreen(navController = navController)
        }
        composable(Screen.Search.route) {
            // SearchScreen(navController = navController)
            // WatchListScreen()
        }
        composable(Screen.WatchList.route) {
            NowPlayScreen(
                navController = navController,
            )
        }
        composable(Screen.Latest.route) {
            // LatestScreen()
        }
        composable(
            route = "Género" + "/{genreId}/{genreName}",
            arguments = listOf(
                navArgument("genreId") { type = NavType.IntType },
                navArgument("genreName") { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            val genreId = backStackEntry.arguments?.getInt("genreId")
            val genreName = backStackEntry.arguments?.getString("genreName")
            MovieWithGenres(navController = navController, genreId = genreId, genreName = genreName)
        }
    }
}
