package com.example.jetmoviesapp.presentation.login.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.jetmoviesapp.presentation.MainScreen
import com.example.jetmoviesapp.presentation.login.model.AuthViewModel
import com.example.jetmoviesapp.presentation.login.utils.AuthResultContract
import com.example.jetmoviesapp.presentation.login.utils.LottieWorkingLoadingView
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AuthView(
    errorText: String?,
    onClick: () -> Unit,
) {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LottieWorkingLoadingView(context = LocalContext.current)
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(top = 8.dp),
        )
        Text(
            text = "We have missed you, Let's start by Sign In!",
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(bottom = 12.dp),
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userEmail,
            label = {
                Text(text = "Email")
            },
            onValueChange = {
                userEmail = it
            },
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            value = userPassword,
            label = {
                Text(text = "Password")
            },
            onValueChange = {
                userPassword = it
            },
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = userEmail.isNotEmpty() && userPassword.isNotEmpty(),
            content = {
                Text(text = "Login")
            },
            onClick = {
                onClick()
            },
        )

        Spacer(modifier = Modifier.height(8.dp))

        GoogleSignInButtonUi(
            text = "Sign Up With Google",
            loadingText = "Signing In....",
            onClicked = { onClick() },
        )
        errorText?.let {
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = it)
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun AuthScreen(
    authViewModel: AuthViewModel,
    // navController : NavController,
) {
    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf<String?>(null) }
    val user by remember(authViewModel) { authViewModel.user }.collectAsState()
    val signInRequestCode = 1

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()) { task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account == null) {
                    text = "Google sign in failed"
                } else {
                    coroutineScope.launch {
                        account.email?.let {
                            account.displayName?.let { it1 ->
                                authViewModel.signIn(
                                    email = it,
                                    displayName = it1,
                                )
                                println("Hola:  ${authViewModel.user.value}")
                            }
                        }
                    }
                }
            } catch (e: ApiException) {
                text = "Google sign in failed"
            }
        }
    AuthView(
        errorText = text,
        onClick = {
            text = null
            authResultLauncher.launch(signInRequestCode)
        },
    )
    user?.let {
        val navController = rememberNavController()
        val showBottomBar = rememberSaveable { mutableStateOf(true) }

        MainScreen(
            navController = navController,
            showBottomBar = showBottomBar,
        )
    }
}
