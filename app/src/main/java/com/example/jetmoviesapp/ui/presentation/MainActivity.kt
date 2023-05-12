package com.example.jetmoviesapp.ui.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetmoviesapp.ui.presentation.composables.drawer.DrawerCompose
import com.example.jetmoviesapp.ui.presentation.home.HomeScreen
import com.example.jetmoviesapp.ui.presentation.loginauth.signin.GoogleAuthUiClient
import com.example.jetmoviesapp.ui.presentation.navigation.AppBottomNavigation
import com.example.jetmoviesapp.ui.presentation.navigation.NavigateScreens
import com.example.jetmoviesapp.ui.presentation.navigation.Screen
import com.example.jetmoviesapp.ui.theme.JetMoviesAppTheme
import com.example.jetmoviesapp.ui.theme.RainbowColors
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            JetMoviesAppTheme {
                val navControllerMain = rememberNavController()
                val lifecycleScope = lifecycleScope
                NavigateScreens(
                    googleAuthUiClient = googleAuthUiClient,
                    navControllerMain = navControllerMain,
                    context = LocalContext.current,
                    lifecycle = lifecycleScope
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    showBottomBar: MutableState<Boolean>,
    navController: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient
) {
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    BackHandler {
    }
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
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch { scaffoldState.drawerState.open() }
                    }) {
                        Icon(
                            Icons.Default.Menu,
                            "",
                            tint = Color.White
                        )
                    }
                },
                modifier = Modifier
                    .padding(top = 30.dp)
                    .height(50.dp)
            )
        },
        drawerShape = RoundedCornerShape(0.dp),
        drawerContent = {
            DrawerCompose(
                userData = googleAuthUiClient.signedInUser(),
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController
            ) {
                googleAuthUiClient.signOut()
                Toast.makeText(
                    context,
                    "Signed Out",
                    Toast.LENGTH_LONG
                ).show()
                navController.navigate(route = Screen.SignIn.route)
                navController.popBackStack(Screen.Home.route, true)
            }
        },
        bottomBar = {
            showBottomBar.value = true
            AnimatedVisibility(
                visible = showBottomBar.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
                AppBottomNavigation(
                    navController = navController,
                    currentDestination = currentDestination
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        backgroundColor = MaterialTheme.colors.surface
    ) { innerPadding ->
        val padd = innerPadding

        HomeScreen(
            navController = navController
        )
    }
}
