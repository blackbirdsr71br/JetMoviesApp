package com.example.jetmoviesapp.ui.presentation.loginauth.signin

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetmoviesapp.R

@Composable
fun LottieWorkingLoadingView() {
    LottieLoadingView(
        file = R.raw.working,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
    )
}
