package com.example.newsapp_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.newsapp_android.bottom_tab.BottomTabNavGraph
import com.example.newsapp_android.bottom_tab.CustomButtomTab
import com.example.newsapp_android.color_schemes.influxnewsDarkColorScheme
import com.example.newsapp_android.color_schemes.influxnewsLightColorScheme
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme
 
class AppContentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InfluxnewsTheme {
                BottomNavigator()
            }
        }
    }
}

@Composable
fun InfluxnewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) influxnewsDarkColorScheme else influxnewsLightColorScheme

    MaterialTheme(content = content, colorScheme = colorScheme)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigator() {
    val navController = rememberNavController()

    Scaffold(bottomBar = { CustomButtomTab(navController = navController) },) {
        Modifier.padding(it).background(color = MaterialTheme.colorScheme.primary)
        BottomTabNavGraph(navController = navController)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    NewsAppandroidTheme {

    }
}