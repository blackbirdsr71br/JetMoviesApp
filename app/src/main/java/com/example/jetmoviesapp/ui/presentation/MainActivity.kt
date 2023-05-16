package com.example.jetmoviesapp.ui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.jetmoviesapp.BuildConfig
import com.example.jetmoviesapp.domain.GoogleAuthUiClient
import com.example.jetmoviesapp.ui.presentation.navigation.NavigateScreens
import com.example.jetmoviesapp.ui.theme.JetMoviesAppTheme
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint

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

