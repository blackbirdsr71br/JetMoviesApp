package com.example.jetmoviesapp.ui.presentation.genres

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetmoviesapp.ui.presentation.composables.JetMoviesTopBar
import com.example.jetmoviesapp.ui.presentation.navigation.Screen
import com.example.jetmoviesapp.ui.theme.genreBgColor
import com.example.jetmoviesapp.ui.theme.genreColor
import com.example.remote.data.remote.genre.Genre

@Composable
fun GenresScreen(
    viewModel: GenresViewModel = hiltViewModel(),
    navController: NavController
) {
    val genres = viewModel.stateGenres.value
    Scaffold(
        topBar = {
            JetMoviesTopBar(
                title = "Genres List",
                backGroundColor = Color.Transparent,
                navController = navController
            )
        },
        modifier = Modifier.statusBarsPadding()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(it)
        ) {
            items(genres) { genre ->
                GenresItem(genre = genre, onClick = {
                    navController.navigate(route = Screen.GenresDetail.route + "/${genre.id}/${genre.name}")
                })
            }
        }
    }
}

@Composable
fun GenresItem(genre: com.example.remote.data.remote.genre.Genre, onClick: (com.example.remote.data.remote.genre.Genre) -> Unit) {
    Card(
        elevation = 2.dp,
        shape = RoundedCornerShape(50),
        backgroundColor = genreBgColor,
        contentColor = genreColor,
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick(genre) }
    ) {
        Text(
            text = genre.name,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 8.dp),
            fontSize = 18.sp
        )
    }
}
