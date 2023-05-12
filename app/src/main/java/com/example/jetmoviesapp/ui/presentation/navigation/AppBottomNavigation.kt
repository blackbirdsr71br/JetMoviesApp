package com.example.jetmoviesapp.ui.presentation.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun AppBottomNavigation(
    navController: NavController,
    currentDestination: NavDestination?
) {
    BottomNavigation(
        modifier = Modifier.navigationBarsPadding(),
        backgroundColor = Color.White
    ) {
        val items = listOf(
            Screen.Home.route to Icons.Filled.Home,
            Screen.Room.route to Icons.Filled.Create,
            Screen.Genres.route to Icons.Filled.Category,
            Screen.PlayNow.route to Icons.Filled.VideoLibrary,
            Screen.Latest.route to Icons.Filled.AccessTime
        )

        items.forEach { pair: Pair<String, ImageVector> ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == pair.first } == false,
                icon = { Icon(imageVector = pair.second, contentDescription = "") },
                label = {
                    Text(
                        text = pair.first.uppercase(),
                        color = Color.Black,
                        fontSize = 10.sp
                    )
                },
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(pair.first) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = false
                    }
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black
            )
        }
    }
}
