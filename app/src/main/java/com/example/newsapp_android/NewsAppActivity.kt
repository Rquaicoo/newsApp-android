package com.example.newsapp_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme
import com.example.newsapp_android.ui.authentication.Login
import com.example.newsapp_android.ui.authentication.Register
import com.example.newsapp_android.ui.main.Home

class NewsAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppandroidTheme {
                NewsApp(modifier = Modifier)
            }
        }
    }
}

@Composable
fun NewsApp(modifier: Modifier) {
    val navController = rememberNavController()

        NavHost(navController, Onboarding.route, modifier) {
            composable(Onboarding.route) {
                OnboardingComponent(OnRegisterClick = { navController.navigate("register") }, OnLoginClick = { navController.navigate("login") }, OnUserVerified = { navController.navigate("home") })
            }
            composable(Login.route) {
                Login( OnLoginSuccess = { navController.navigate(("home"))} )
            }
            composable(Register.route) {
                Register( OnRegisterSuccess = { navController.navigate(("home"))} )
            }
            composable(Home.route) {
                Home()
            }
        }

}
