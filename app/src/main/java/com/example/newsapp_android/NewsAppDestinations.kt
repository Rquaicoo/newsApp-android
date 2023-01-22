package com.example.newsapp_android

import androidx.compose.runtime.Composable
import com.example.newsapp_android.ui.authentication.Login
import com.example.newsapp_android.ui.authentication.Register

interface NewsAppDestination {
    val route : String
}

object Onboarding : NewsAppDestination {
    override val route = "onboarding"
}

object Register : NewsAppDestination {
    override val route = "register"
}

object Login : NewsAppDestination {
    override val route = "login"
}

object Home : NewsAppDestination {
    override val route = "home"
}

