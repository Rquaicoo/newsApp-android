package com.example.newsapp_android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp_android.R
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme

val sections = listOf(R.string.news, R.string.sports, R.string.politics, R.string.tech)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SectionComponent(modifier: Modifier = Modifier, sectionName: String) {
    var pressed by rememberSaveable { mutableStateOf(false) }

    var textColor = Color(0x00, 0x27, 0x54)
    var backgroundColor = Color.White


    Button(
        onClick = {
            pressed = !pressed

            if (pressed) {
                textColor = Color.White
                backgroundColor = Color(0x00, 0x27, 0x54)
            }

            else {
                textColor = Color(0x00, 0x27, 0x54)
                backgroundColor = Color.White
            }
        },
        modifier = Modifier
            .height(40.dp)
            .widthIn(min = 10.dp),
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(if (pressed) Color(0x00, 0x27, 0x54) else Color.White )
    ) {
        Text(
            sectionName,
            color = if (pressed) Color.White else Color(0x00, 0x27, 0x54),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.arial)),
            fontSize = 16.sp, lineHeight = 2.sp,
            modifier = Modifier
                .padding(horizontal = 2.dp, vertical = 1.dp)

        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SectionsComponent(modifier: Modifier = Modifier) {
    LazyRow(modifier = Modifier
        .height(100.dp)
        .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(sections) {
            item -> SectionComponent(sectionName = stringResource(id = item))
        }
    }

}



@Preview(widthDp = 360, heightDp = 100, showBackground = true)
@Composable
fun SectionPreview() {
    NewsAppandroidTheme {
        SectionComponent(sectionName = "News")
    }
}

@Preview(widthDp = 360, heightDp = 150, showBackground = true)
@Composable
fun SectionsPreview() {
    NewsAppandroidTheme {
        SectionsComponent()
    }
}