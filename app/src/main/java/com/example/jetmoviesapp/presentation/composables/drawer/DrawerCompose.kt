package com.example.jetmoviesapp.presentation.composables.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.jetmoviesapp.R
import com.example.jetmoviesapp.presentation.loginauth.signin.UserData
import com.example.jetmoviesapp.theme.spacing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerCompose(
    userData: UserData?,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    onSignOut: () -> Unit,
) {
    Column(
        Modifier
            .testTag(stringResource(R.string.drawer_test_tag))
            .fillMaxSize()
            .background(Color.White),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.secondary),
            ) {
                if (userData?.profilePicture != null) {
                    AsyncImage(
                        model = userData.profilePicture,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                if (userData?.userName != null) {
                    Text(
                        text = userData.userName,
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Spacer(modifier = Modifier.height(spacing.small))
            }
        }

        Column(Modifier.padding(spacing.medium20)) {
            DrawerItem(
                R.drawable.ic_home,
                R.string.dashboard,
            ) {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }

            DrawerItem(
                R.drawable.ic_logout,
                R.string.logout,
            ) {
                onSignOut()
                navController.popBackStack("home", true)
            }
        }
    }
}

@Composable
fun DrawerItem(icon: Int, title: Int, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(spacing.extraLarge60)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .width(spacing.medium25)
                .height(spacing.medium25),
            painter = painterResource(icon),
            contentDescription = "",
        )
        Spacer(modifier = Modifier.width(spacing.extraLarge60))
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.h6,
        )
    }

    Divider(color = MaterialTheme.colors.primary)
}
