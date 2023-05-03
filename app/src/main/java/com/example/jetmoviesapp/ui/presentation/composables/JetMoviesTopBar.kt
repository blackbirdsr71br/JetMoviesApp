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

@Composable
fun JetMoviesTopBar(
    title: String,
    backGroundColor: Color,
    navController: NavController,
) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = backGroundColor,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(route = "home") {
                    navController.popBackStack(route = "movie_details", inclusive = true)
                    navController.popBackStack(route = "popular", inclusive = true)
                    navController.popBackStack(route = "latest", inclusive = true)
                    navController.popBackStack(route = "room", inclusive = true)
                    navController.popBackStack(route = "genres", inclusive = true)
                    navController.popBackStack(route = "play_now", inclusive = true)
                }
            }) {
                Icon(
                    Icons.Default.ArrowBack,
                    "",
                    tint = Color.Black,
                )
            }
        },
    )
}
