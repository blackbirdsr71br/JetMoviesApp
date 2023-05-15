package com.example.jetmoviesapp.ui.presentation.latest

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
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
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.common.Constants
import com.example.jetmoviesapp.R
import com.example.jetmoviesapp.ui.presentation.composables.JetMoviesTopBar
import com.example.jetmoviesapp.ui.presentation.composables.MovieItem
import com.example.jetmoviesapp.ui.presentation.navigation.Screen
import com.example.jetmoviesapp.ui.theme.ratingStarColor
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun LatestScreen(
    viewModel : LatestMoviesViewModel = hiltViewModel(),
    navController : NavController,
) {
    val latestlist = viewModel.latest.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            JetMoviesTopBar(
                title = "Lates movies",
                backGroundColor = Color.Transparent,
                navController = navController
            )
        },
        modifier = Modifier.statusBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues = it)
        ) {
            items(
                count = latestlist.itemCount,
                key = latestlist.itemKey(),
                contentType = latestlist.itemContentType()
            ) { index ->
                val item = latestlist[index]
                item?.let { topRated ->
                    MovieItem(movie = topRated) { navigatedItem ->
                        navController.navigate(route = Screen.MovieDetail.route + "/${navigatedItem.id}")
                    }
                }
            }
            latestlist.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        // when first time response page is loading
                        item {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                CircularProgressIndicator(color = Color.DarkGray)
                            }
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            LinearProgressIndicator(
                                color = Color.Red,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .statusBarsPadding()
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
