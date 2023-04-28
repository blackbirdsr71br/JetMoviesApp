package com.example.jetmoviesapp.ui.presentation.latest

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetmoviesapp.data.remote.latest.Latest
import com.example.jetmoviesapp.ui.theme.ratingStarColor

@Composable
fun LatestScreen(
    viewModel: LatestMoviesViewModel = hiltViewModel(),
) {
    val latestList = viewModel.stateGenres.value
    println("Latest*********** ")
    println("Latest*********** ")
    println("Latest*********** = $latestList")
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Latest Movies") },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
            )
        },
        modifier = Modifier.statusBarsPadding(),
    ) {
        if (latestList != null) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues = it),
            ) {
                item {
                    latestList.forEach() { latest ->
                        LatestMovieItem(latest = latest)
                    }
                }
            }
        }
    }
}

@Composable
fun LatestMovieItem(latest: Latest) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        modifier = Modifier
            .height(220.dp)
            .padding(12.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .height(200.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(latest.originalTitle)
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Gray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light,
                        ),
                    ) {
                        append(" (${latest.title}) ")
                    }
                }
                Text(text = annotatedString, style = MaterialTheme.typography.h6)
                Row {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "",
                        modifier = Modifier.size(24.dp),
                        tint = ratingStarColor,
                    )
                }
            }
        }
    }
}
