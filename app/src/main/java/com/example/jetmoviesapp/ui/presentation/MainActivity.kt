package com.example.jetmoviesapp.ui.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetmoviesapp.ui.presentation.composables.drawer.DrawerCompose
import com.example.jetmoviesapp.ui.presentation.detail.MovieDetailScreen
import com.example.jetmoviesapp.ui.presentation.genres.GenresScreen
import com.example.jetmoviesapp.ui.presentation.home.HomeScreen
import com.example.jetmoviesapp.ui.presentation.latest.LatestScreen
import com.example.jetmoviesapp.ui.presentation.loginauth.profile.ProfileScreen
import com.example.jetmoviesapp.ui.presentation.loginauth.signin.GoogleAuthUiClient
import com.example.jetmoviesapp.ui.presentation.loginauth.signin.SignInScreen
import com.example.jetmoviesapp.ui.presentation.loginauth.signin.SignInViewmodel
import com.example.jetmoviesapp.ui.presentation.movie_genres.MovieWithGenres
import com.example.jetmoviesapp.ui.presentation.navigation.AppBottomNavigation
import com.example.jetmoviesapp.ui.presentation.navigation.Screen
import com.example.jetmoviesapp.ui.presentation.now_play.NowPlayScreen
import com.example.jetmoviesapp.ui.presentation.popular.PopularMoviesScreen
import com.example.jetmoviesapp.ui.presentation.top_rated.TopRatedScreen
import com.example.jetmoviesapp.ui.presentation.watch_list.WatchListScreen
import com.example.jetmoviesapp.ui.theme.JetMoviesAppTheme
import com.example.jetmoviesapp.ui.theme.RainbowColors
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            JetMoviesAppTheme {
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = MaterialTheme.colors.isLight

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = useDarkIcons,
                    )
                }
                val navControllerMain = rememberNavController()
                NavHost(navController = navControllerMain, startDestination = "sign_in") {
                    composable("sign_in") {
                        val viewModel = viewModel<SignInViewmodel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = Unit) {
                            if (googleAuthUiClient.signedInUser() != null) {
                                navControllerMain.navigate("home")
                                viewModel.resetState()
                            }
                        }
                        val launcher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = { result ->
                                if (result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
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
                                    applicationContext,
                                    "Sign In Succesful",
                                    Toast.LENGTH_LONG,
                                ).show()
                                navControllerMain.navigate("home")
                                viewModel.resetState()
                            }
                        }
                        SignInScreen(
                            state = state,
                            onSignInClick = {
                                lifecycleScope.launch {
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
                        route = "home",
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
                        route = "profile",
                    ) {
                        ProfileScreen(
                            userData = googleAuthUiClient.signedInUser(),
                            onSignOut = {
                                lifecycleScope.launch {
                                    googleAuthUiClient.signOut()
                                    Toast.makeText(
                                        applicationContext,
                                        "Signed Out",
                                        Toast.LENGTH_LONG,
                                    ).show()
                                    navControllerMain.navigate(route = "sign_in")
                                }
                            },
                            navController1 = navControllerMain,
                        )
                    }
                    composable(route = "top_rated") {
                        TopRatedScreen(navController = navControllerMain)
                    }
                    composable(route = "popular") {
                        PopularMoviesScreen(navController = navControllerMain)
                    }
                    composable(route = "movie_detail" + "/{movie_id}") {
                        MovieDetailScreen(navController = navControllerMain)
                    }
                    composable(route = "genres") {
                        GenresScreen(navController = navControllerMain)
                    }
                    composable(route = "Room") {
                        // SearchScreen(navController = navController)
                        WatchListScreen()
                    }
                    composable(route = "play_now") {
                        NowPlayScreen(
                            navController = navControllerMain,
                        )
                    }
                    composable(route = "latest") {
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
                        MovieWithGenres(
                            navController = navControllerMain,
                            genreId = genreId,
                            genreName = genreName,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    showBottomBar: MutableState<Boolean>,
    navController: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient,
) {
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "JetMovies",
                        modifier = Modifier
                            .graphicsLayer(alpha = 0.99f)
                            .drawWithCache {
                                val brush = Brush.horizontalGradient(RainbowColors)
                                onDrawWithContent {
                                    drawContent()
                                    drawRect(brush, blendMode = BlendMode.SrcAtop)
                                }
                            },
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch { scaffoldState.drawerState.open() }
                    }) {
                        Icon(
                            Icons.Default.Menu,
                            "",
                            tint = Color.White,
                        )
                    }
                },
                modifier = Modifier
                    .padding(top = 30.dp)
                    .height(50.dp),
            )
        },
        drawerShape = RoundedCornerShape(0.dp),
        drawerContent = {
            DrawerCompose(
                userData = googleAuthUiClient.signedInUser(),
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController,
            ) {
                googleAuthUiClient.signOut()
                Toast.makeText(
                    context,
                    "Signed Out",
                    Toast.LENGTH_LONG,
                ).show()
                navController.navigate(route = "sign_in")
                navController.popBackStack("home", true)
            }
        },
        bottomBar = {
            showBottomBar.value = true
            AnimatedVisibility(
                visible = showBottomBar.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
            ) {
                AppBottomNavigation(
                    navController = navController,
                    currentDestination = currentDestination,
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        backgroundColor = MaterialTheme.colors.surface,
    ) { innerPadding ->
        val padd = innerPadding

        HomeScreen(
            navController = navController,
        )
    }
}
