package com.example.jetmoviesapp.ui.presentation.navigation

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jetmoviesapp.ui.presentation.MainScreen
import com.example.jetmoviesapp.ui.presentation.detail.MovieDetailScreen
import com.example.jetmoviesapp.ui.presentation.genres.GenresScreen
import com.example.jetmoviesapp.ui.presentation.latest.LatestScreen
import com.example.jetmoviesapp.ui.presentation.loginauth.profile.ProfileScreen
import com.example.jetmoviesapp.ui.presentation.loginauth.signin.GoogleAuthUiClient
import com.example.jetmoviesapp.ui.presentation.loginauth.signin.SignInScreen
import com.example.jetmoviesapp.ui.presentation.loginauth.signin.SignInViewmodel
import com.example.jetmoviesapp.ui.presentation.movie_genres.MovieWithGenres
import com.example.jetmoviesapp.ui.presentation.now_play.NowPlayScreen
import com.example.jetmoviesapp.ui.presentation.popular.PopularMoviesScreen
import com.example.jetmoviesapp.ui.presentation.top_rated.TopRatedScreen
import com.example.jetmoviesapp.ui.presentation.watch_list.WatchListScreen
import kotlinx.coroutines.launch

@Composable
fun NavigateScreens(
    navControllerMain: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient,
    context: Context,
    lifecycle: LifecycleCoroutineScope,
) {
    NavHost(
        navController = navControllerMain,
        startDestination = Screen.SignIn.route,
    ) {
        composable(
            Screen.SignIn.route,
        ) {
            val viewModel = viewModel<SignInViewmodel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit) {
                if (googleAuthUiClient.signedInUser() != null) {
                    navControllerMain.navigate(Screen.Home.route)
                    viewModel.resetState()
                }
            }
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == ComponentActivity.RESULT_OK) {
                        lifecycle.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch,
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                },
            )

            LaunchedEffect(key1 = state.isSignInSuccesful) {
                if (state.isSignInSuccesful) {
                    Toast.makeText(
                        context,
                        "Sign In Succesful",
                        Toast.LENGTH_LONG,
                    ).show()
                    navControllerMain.navigate(Screen.Home.route)
                    viewModel.resetState()
                }
            }
            SignInScreen(
                state = state,
                onSignInClick = {
                    lifecycle.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch,
                            ).build(),
                        )
                    }
                },
            )
        }
        composable(
            route = Screen.Home.route,
        ) {
            // Main Screen
            val showBottomBar = rememberSaveable { mutableStateOf(true) }
            MainScreen(
                navController = navControllerMain,
                showBottomBar = showBottomBar,
                googleAuthUiClient = googleAuthUiClient,
            )
        }
        composable(
            Screen.Profile.route,
        ) {
            val scope = rememberCoroutineScope()
            ProfileScreen(
                userData = googleAuthUiClient.signedInUser(),
                onSignOut = {
                    scope.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(
                            context,
                            "Signed Out",
                            Toast.LENGTH_LONG,
                        ).show()
                        navControllerMain.navigate(route = "sign_in")
                    }
                },
                navController1 = navControllerMain,
            )
        }
        composable(
            Screen.TopRated.route,
        ) {
            TopRatedScreen(navController = navControllerMain)
        }
        composable(
            Screen.Popular.route,
        ) {
            PopularMoviesScreen(navController = navControllerMain)
        }
        composable(
            Screen.MovieDetail.route + "/{movie_id}",
        ) {
            MovieDetailScreen(navController = navControllerMain)
        }
        composable(
            Screen.Genres.route,
        ) {
            GenresScreen(navController = navControllerMain)
        }
        composable(
            Screen.Room.route,
        ) {
            // SearchScreen(navController = navController)
            WatchListScreen(
                navController = navControllerMain,
            )
        }
        composable(
            Screen.PlayNow.route,
        ) {
            NowPlayScreen(
                navController = navControllerMain,
            )
        }
        composable(
            Screen.Latest.route,
        ) {
            LatestScreen(
                navController = navControllerMain,
            )
        }
        composable(
            route = Screen.GenresDetail.route + "/{genreId}/{genreName}",
            arguments = listOf(
                navArgument("genreId") { type = NavType.IntType },
                navArgument("genreName") { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            val genreId = backStackEntry.arguments?.getInt("genreId")
            val genreName = backStackEntry.arguments?.getString("genreName")
            MovieWithGenres(
                navController = navControllerMain,
                genreId = genreId,
                genreName = genreName,
            )
        }
    }
}
