package com.example.jetmoviesapp.presentation.loginauth.signin

data class SignInState(
    val isSignInSuccesful: Boolean = false,
    val signInError: String? = null,
)
