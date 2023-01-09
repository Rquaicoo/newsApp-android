package com.example.newsapp_android

import androidx.compose.runtime.Composable
import com.example.newsapp_android.ui.authentication.Login
import com.example.newsapp_android.ui.authentication.Register

interface NewsAppDestination {
    val route : String
    val screen : @Composable () -> Unit
}

object Onboarding : NewsAppDestination {
    override val route = "onboarding"
    override val screen : @Composable () -> Unit = { OnboardingComponent() }
}

object Register : NewsAppDestination {
    override val route = "register"
    override val screen : @Composable () -> Unit = { Register() }
}

object Login : NewsAppDestination {
    override val route = "login"
    override val screen : @Composable () -> Unit = { Login() }
}

object Home : NewsAppDestination {
    override val route = "home"
    /*TODO: To be modified*/
    override val screen : @Composable () -> Unit = { OnboardingComponent() }
}

