package com.example.jetmoviesapp.ui.presentation.loginauth.signin

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetmoviesapp.R
import com.example.jetmoviesapp.ui.theme.Shapes

@ExperimentalMaterialApi
@Composable
fun GoogleSignInButtonUi(
    text: String = "",
    loadingText: String = "",
    onClicked: () -> Unit
) {
    var clicked by remember { mutableStateOf(false) }
    Surface(
        onClick = { clicked = !clicked },
        shape = Shapes.medium,
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
        color = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .animateContentSize(
                    animationSpec = tween(durationMillis = 200, easing = LinearOutSlowInEasing)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = "Google sign button",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = if (clicked) loadingText else text)

            if (clicked) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier.height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colors.primary
                )
                onClicked()
            }
        }
    }
}

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    buttonText: String,
    onClick: () -> Unit,
    backgroundColor: Color,
    fontColor: Color
) {
    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .shadow(0.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        shape = RoundedCornerShape(28.dp),
        contentPadding = PaddingValues(15.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = fontColor
        ),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
            ) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = imageVector,
                    modifier = Modifier
                        .size(18.dp),
                    contentDescription = "drawable_icons",
                    tint = Color.Unspecified
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = buttonText,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun GoogleButtonPreview() {
    GoogleSignInButtonUi(
        text = "Sign Up With Google",
        loadingText = "Signing In....",
        onClicked = {}
    )
}
