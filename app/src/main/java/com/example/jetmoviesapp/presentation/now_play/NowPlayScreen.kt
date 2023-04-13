package com.example.jetmoviesapp.presentation.now_play

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.jetmoviesapp.R
import com.example.jetmoviesapp.common.Constants
import com.example.jetmoviesapp.data.remote.movie.Movie
import com.example.jetmoviesapp.presentation.navigation.AppBottomNavigation
import com.example.jetmoviesapp.presentation.navigation.Screen
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import com.example.jetmoviesapp.theme.ratingStarColor

@Composable
fun NowPlayScreen(
    viewModel: NowPlayViewModel = hiltViewModel(),
    navController: NavController,
) {
    val topRatedList = viewModel.nowPlay.collectAsLazyPagingItems()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Now Playing") },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
            )
        },
        modifier = Modifier.statusBarsPadding(),
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = it),
        ) {
            items(topRatedList) { item ->
                item?.let { topRated ->
                    NowPlayItem(topRated = topRated) { navigatedItem ->
                        navController.navigate(Screen.MovieDetail.route + "/${navigatedItem.id}")
                    }
                }
            }
            topRatedList.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        // when first time response page is loading
                        item { CircularProgressIndicator(color = Color.DarkGray) }
                    }
                    loadState.append is LoadState.Loading -> {
                        item {
                            LinearProgressIndicator(
                                color = Color.Red,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .statusBarsPadding(),
                            )
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

@Composable
fun NowPlayItem(topRated: Movie, onClick: (Movie) -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        modifier = Modifier
            .height(220.dp)
            .padding(12.dp)
            .clickable {
                onClick(topRated)
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            CoilImage(
                imageModel = Constants.IMAGE_URL + topRated.posterPath,
                contentScale = ContentScale.Crop,
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = Color.LightGray.copy(alpha = 0.6f),
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f,
                ),
                circularReveal = CircularReveal(duration = 350),
                failure = { Text(text = "Image request failed!") },
                modifier = Modifier
                    .height(200.dp)
                    .width(120.dp)
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp)),
            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .height(200.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(topRated.originalTitle)
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Gray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light,
                        ),
                    ) {
                        append(" (${topRated.title}) ")
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
                    Text(text = "${topRated.voteAverage}/10", color = Color.LightGray)
                }
                Text(
                    text = topRated.overview,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle2,
                )
            }
        }
    }
}
