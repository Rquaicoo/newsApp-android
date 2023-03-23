package com.example.newsapp_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.newsapp_android.bottom_tab.BottomTabNavGraph
import com.example.newsapp_android.bottom_tab.CustomButtomTab
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme
 
class AppContentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppandroidTheme {
                BottomNavigator()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigator() {
    val navController = rememberNavController()

    Scaffold(bottomBar = { CustomButtomTab(navController = navController) }) {
        Modifier.padding(it)
        BottomTabNavGraph(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    NewsAppandroidTheme {

    }
}