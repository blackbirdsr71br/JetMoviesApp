package com.example.jetmoviesapp.presentation.login.utils

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetmoviesapp.R
import com.example.jetmoviesapp.presentation.login.utils.LottieLoadingView

@Composable
fun LottieWorkingLoadingView(context: Context) {
    LottieLoadingView(
        context = context,
        file = R.raw.working,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    )
}
