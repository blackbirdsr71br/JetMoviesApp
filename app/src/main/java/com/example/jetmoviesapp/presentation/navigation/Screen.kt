package com.example.jetmoviesapp.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "home")
    object TopRated : Screen(route = "top_rated")
    object Popular : Screen(route = "popular")
    object MovieDetail : Screen(route = "movie_detail")
    object Genres : Screen(route = "genres")
    object Search : Screen(route = "Room")
    object WatchList : Screen(route = "play now")
    object MovieWithGenres : Screen(route = "Género")
    object LoginScreen : Screen(route = "Login")
    object Start : Screen(route = "Inicio")
}
