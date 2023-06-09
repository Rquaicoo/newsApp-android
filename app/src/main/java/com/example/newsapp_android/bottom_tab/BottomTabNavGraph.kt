package com.example.newsapp_android.bottom_tab

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp_android.ui.main.BookmarksScreen
import com.example.newsapp_android.ui.main.HomeScreen
import com.example.newsapp_android.ui.main.ProfileScreen
import com.example.newsapp_android.ui.main.SearchScreen

@Composable
fun BottomTabNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Home.route) {
        composable(route = Home.route) {
            HomeScreen()
        }
        composable(route = Bookmarks.route) {
            BookmarksScreen()
        }
        composable(route = Search.route) {
            SearchScreen()
        }
        composable(route = Profile.route) {
            ProfileScreen(OnSignOut = { })
        }
    }
}