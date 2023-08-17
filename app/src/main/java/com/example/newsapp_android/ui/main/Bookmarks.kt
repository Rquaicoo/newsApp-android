package com.example.newsapp_android.ui.main


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp_android.R
import com.example.newsapp_android.components.SectionComponent
import com.example.newsapp_android.components.calculateTimeDifference
import com.example.newsapp_android.dataclasses.PostsModel
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme
import com.example.newsapp_android.utilities.openLink
import java.util.*

@Composable
fun BookmarksScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.primary)) {
        Column(modifier = Modifier) {
        }
    }
}

@Preview(widthDp = 360, heightDp = 300, showBackground = true)
@Composable
fun MovieTilePreview() {
    NewsAppandroidTheme {
        BookmarksScreen()
    }
}