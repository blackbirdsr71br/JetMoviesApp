package com.example.jetmoviesapp.ui.presentation.loginauth.signin

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.jetmoviesapp.R

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieWorkingLoadingView()
            Text(
                text = "Welcome Back",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "We have missed you, Let's start by Sign In!",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(bottom = 12.dp)
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
                }
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
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = userEmail.isNotEmpty() && userPassword.isNotEmpty(),
                content = {
                    Text(text = "Login with email")
                },
                onClick = {
                    onSignInClick.invoke()
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            GoogleButton(
                imageVector = ImageVector.vectorResource(R.drawable.ic_google_logo),
                buttonText = "Sign In with Google",
                onClick = onSignInClick,
                backgroundColor = Color.White,
                fontColor = Color.Black
            )
        }
    }
}
