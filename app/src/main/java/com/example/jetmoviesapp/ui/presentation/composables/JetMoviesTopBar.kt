package com.example.jetmoviesapp.ui.presentation.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetmoviesapp.ui.presentation.navigation.Screen

@Composable
fun JetMoviesTopBar(
    title: String,
    backGroundColor: Color,
    navController: NavController
) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = backGroundColor,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(route = "home") {
                    navController.popBackStack(route = Screen.MovieDetail.route, inclusive = true)
                    navController.popBackStack(route = Screen.Popular.route, inclusive = true)
                    navController.popBackStack(route = Screen.Latest.route, inclusive = true)
                    navController.popBackStack(route = Screen.Room.route, inclusive = true)
                    navController.popBackStack(route = Screen.Genres.route, inclusive = true)
                    navController.popBackStack(route = Screen.PlayNow.route, inclusive = true)
                }
            }) {
                Icon(
                    Icons.Default.ArrowBack,
                    "",
                    tint = Color.Black
                )
            }
        }
    )
}
