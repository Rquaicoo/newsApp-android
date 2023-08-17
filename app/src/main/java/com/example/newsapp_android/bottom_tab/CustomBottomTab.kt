package com.example.newsapp_android.bottom_tab

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp_android.R
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme

@Composable
fun CustomButtomTab(modifier: Modifier = Modifier, navController: NavController) {
     val screens = listOf(
         Home,
         Bookmarks,
         Search,
         Profile
     )

    val navStackBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackStackEntry?.destination
    var selected by remember { mutableStateOf(1) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 73.dp)
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            Box(modifier = Modifier.width(40.dp)) {
                Icon(
                    painter = painterResource(id = screen.icon),
                    tint = if (screen.id == selected) Color(0xF6, 0x76, 0x00) else MaterialTheme.colorScheme.secondary,
                    contentDescription = screen.name,
                    modifier = Modifier
                        .clickable {
                            selected = screen.id
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        }
                )
            }
        }
    }
}

@Composable
@Preview()
fun BottomTabPreview() {
    NewsAppandroidTheme() {
        CustomButtomTab(navController = rememberNavController())
    }
}