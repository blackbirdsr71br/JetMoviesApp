package com.example.jetmoviesapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetmoviesapp.presentation.latest.LatestScreen
import com.example.jetmoviesapp.presentation.detail.MovieDetailScreen
import com.example.jetmoviesapp.presentation.genres.GenresScreen
import com.example.jetmoviesapp.presentation.home.HomeScreen
import com.example.jetmoviesapp.presentation.login.model.AuthViewModel
import com.example.jetmoviesapp.presentation.login.presentation.AuthScreen
import com.example.jetmoviesapp.presentation.movie_genres.MovieWithGenres
import com.example.jetmoviesapp.presentation.now_play.NowPlayScreen
import com.example.jetmoviesapp.presentation.popular.PopularMoviesScreen
import com.example.jetmoviesapp.presentation.top_rated.TopRatedScreen
import com.example.jetmoviesapp.presentation.watch_list.WatchListScreen
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
        composable(Screen.LoginScreen.route) {
            AuthScreen(authViewModel = AuthViewModel())
        }
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
            WatchListScreen()
        }
        composable(Screen.WatchList.route) {
            NowPlayScreen(
                navController = navController,
            )
        }
        composable(Screen.Latest.route) {
            LatestScreen()
        }
        composable(
            Screen.MovieWithGenres.route + "/{genreId}/{genreName}",
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
