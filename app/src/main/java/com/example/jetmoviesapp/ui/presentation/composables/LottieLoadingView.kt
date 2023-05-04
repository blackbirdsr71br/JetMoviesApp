package com.example.jetmoviesapp.ui.presentation.loginauth.signin

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieLoadingView(
    file: Int,
    modifier: Modifier = Modifier,
    iterations: Int = LottieConstants.IterateForever,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(file))
    LottieAnimation(
        composition,
        modifier = modifier.defaultMinSize(300.dp),
        iterations = iterations,
    )

    // OLD ANDROID VIEW IMPLEMENTATION
//    val lottieView = remember {
//        LottieAnimationView(context).apply {
//            setAnimation(file)
//            repeatCount = ValueAnimator.INFINITE
//        }
//    }
//    AndroidView(
//        { lottieView },
//        modifier = modifier
//    ) {
//        it.playAnimation()
//    }
}
