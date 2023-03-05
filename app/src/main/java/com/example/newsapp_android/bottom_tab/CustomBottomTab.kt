package com.example.newsapp_android.bottom_tab

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp_android.R
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme

@Composable
fun CustomButtomTab(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 73.dp)
            .background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.news_page_icon),
            contentDescription = "Home icon",
            modifier = Modifier
                .height(24.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.bookmarks),
            contentDescription = "Home icon",
            modifier = Modifier
                .widthIn(min = 14.dp)
                .height(19.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Home icon",
            modifier = Modifier
                .height(17.5.dp)
                .width(17.5.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Home icon",
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
        )

    }
}

@Composable
@Preview()
fun BottomTabPreview() {
    NewsAppandroidTheme() {
        CustomButtomTab()
    }
}