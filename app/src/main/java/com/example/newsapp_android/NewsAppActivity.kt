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
                OnboardingComponent(OnRegisterClick = { navController.navigate("register") }, OnLoginClick = { navController.navigate("login") })
            }
            composable(Login.route) {
                Login.screen()
            }
            composable(Register.route) {
                Register.screen()
            }
            composable(Home.route) {
                Home.screen()
            }
        }

}
