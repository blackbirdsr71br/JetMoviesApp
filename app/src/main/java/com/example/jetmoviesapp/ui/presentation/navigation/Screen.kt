package com.example.jetmoviesapp.ui.presentation.navigation

sealed class Screen(val route: String) {
    object SignIn : Screen(route = "sign_in")
    object Home : Screen(route = "home")
    object TopRated : Screen(route = "top_rated")
    object Popular : Screen(route = "popular")
    object MovieDetail : Screen(route = "movie_detail")
    object Genres : Screen(route = "genres")
    object Room : Screen(route = "Room")
    object PlayNow : Screen(route = "play_now")
    object Latest : Screen(route = "latest")
    object GenresDetail : Screen(route = "genres_detail")
}
