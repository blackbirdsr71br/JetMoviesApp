package com.example.jetmoviesapp.ui.presentation.loginauth.signin

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val userName: String?,
    val profilePicture: String?
)
