package com.example.newsapp_android

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme
import com.example.newsapp_android.ui.authentication.Login
import com.example.newsapp_android.ui.authentication.Register
import com.example.newsapp_android.ui.main.HomeScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class NewsAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppandroidTheme {
                NewsApp(modifier = Modifier)
            }
        }
    }

    @Composable
    fun NewsApp(modifier: Modifier) {
        val navController = rememberNavController()
        val context = LocalContext.current as Activity

        val switchActivityIntent = Intent(this@NewsAppActivity, AppContentActivity::class.java)
        var auth: FirebaseAuth = Firebase.auth

        LaunchedEffect(Unit) {

            //check if user has signed in
            val currentUser = auth.currentUser
            if (currentUser != null) {
                Log.d(ContentValues.TAG, "checking..")
                print(currentUser)

                startActivity(switchActivityIntent)
            }

        }

        if (auth.currentUser == null) {
            NavHost(navController, Onboarding.route, modifier) {
                composable(Onboarding.route) {
                    OnboardingComponent(
                        OnRegisterClick = { navController.navigate("register") },
                        OnLoginClick = { navController.navigate("login") })
                }
                composable(Login.route) {
                    Login(
                        OnLoginSuccess = { startActivity(switchActivityIntent) },
                        NavigateToRegister = { navController.navigate("register") })
                }
                composable(Register.route) {
                    Register(
                        OnRegisterSuccess = { startActivity(switchActivityIntent) },
                        NavigateToLogin = { navController.navigate("login") })
                }
            }
        }
    }
}
