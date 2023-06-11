package com.example.newsapp_android.components

import android.content.Context
import androidx.compose.foundation.*
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp_android.R
import com.example.newsapp_android.dataclasses.PostsModel
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme
import com.example.newsapp_android.utilities.openLink
import java.util.*

import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import kotlin.collections.HashMap

fun calculateTimeDifference(dateTimeString: String): String {
    val currentDateTime = ZonedDateTime.now()
    val targetDateTime = ZonedDateTime.parse(dateTimeString)

    val minutesDifference = ChronoUnit.MINUTES.between(targetDateTime, currentDateTime)
    val hoursDifference = ChronoUnit.HOURS.between(targetDateTime, currentDateTime)
    val daysDifference = ChronoUnit.DAYS.between(targetDateTime, currentDateTime)

    return when {
        minutesDifference < 60 -> "${Math.round(minutesDifference.toDouble())} mins ago"
        hoursDifference < 100 -> "${Math.round(hoursDifference.toDouble())} hours ago"
        else -> "${Math.round(daysDifference.toDouble())} days ago"
    }
}


@Composable
fun NewsComponent(post: PostsModel, context: Context) {
    Box(
        modifier = Modifier
            .height(329.dp)
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp)
            .clickable(
                onClick = {
                    openLink(context, post.url)
                }
            )

    ) {
        Image(
            painter = rememberAsyncImagePainter(post.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .clip(shape = RoundedCornerShape(10.dp)),
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 25.dp)
                .background(
                    color = Color(0xA4, 0xB6, 0xC5),
                    shape = RoundedCornerShape(20.dp)
                )
                .height(28.dp)
                .widthIn(min = 10.dp)
                .padding(horizontal = 4.dp, vertical = 1.dp)
        ) {
            Text(
                post.category.replaceFirstChar {
                    it
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                },
                lineHeight = 17.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily(Font(R.font.arial)),
                color = Color.White,
                modifier = Modifier.padding(
                    horizontal = 10.dp,
                    vertical = 1.dp
                )
            )
        }



        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(
                    color = Color(0xFF, 0xFF, 0xFF, 0x99),
                    shape = RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp)
                )
        ) {
            Text(
                post.author.name,
                lineHeight = 15.sp,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily(Font(R.font.arial)),
                color = Color(0x4E, 0x4E, 0x4E),
                modifier = Modifier.padding(
                    horizontal = 5.dp,
                    vertical = 1.dp
                )
            )

            Text(
                post.title,
                lineHeight = 17.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.arial)),
                color = Color.Black,
                maxLines = 2,
                modifier = Modifier.padding(
                    horizontal = 5.dp,
                    vertical = 1.dp
                ),

                overflow = TextOverflow.Ellipsis
            )

            Row(modifier = Modifier) {
                Spacer(Modifier.weight(1f))

                Text(
                    calculateTimeDifference(post.date_scraped),
                    lineHeight = 17.sp,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily(Font(R.font.arial)),
                    color = Color.Black,
                    modifier = Modifier.padding(
                        horizontal = 5.dp,
                        vertical = 1.dp
                    )
                )
            }
        }
    }
}


@Composable
fun TrendingItemComponent(post: PostsModel, context: Context, modifier: Modifier = Modifier) {
    /*Sample Trending Post*/

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            .height(129.dp)
            .clickable { openLink(context, post.url) }
    ) {
        Column(modifier = Modifier.width(200.dp)) {
            Row(modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(post.author.image),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 1.dp)
                        .width(14.dp)
                        .height(14.dp)
                        .clip(shape = CircleShape)

                )

                Text(
                    post.author.name,
                    lineHeight = 15.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.arial)),
                    color = Color(0x4E, 0x4E, 0x4E),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 1.dp)
                )
            }

            Text(
                post.title,
                lineHeight = 15.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.arial)),
                color = Color(0x4E, 0x4E, 0x4E),
                maxLines = 2,
                modifier = Modifier.padding(horizontal = 15.dp),
                overflow = TextOverflow.Ellipsis

            )

            Row(Modifier.padding(vertical = 5.dp, horizontal = 20.dp),verticalAlignment = Alignment.CenterVertically) {
                Canvas(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .size(6.dp)
                ) {
                    drawCircle(Color(0x4E, 0x4E, 0x4E))
                }

                Text(
                    calculateTimeDifference(post.date_scraped),
                    lineHeight = 15.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.arial)),
                    color = Color(0x4E, 0x4E, 0x4E),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }

        }

        Spacer(modifier.weight(1f))

        Image(
            painter = rememberAsyncImagePainter(post.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(102.dp)
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(0.dp, 10.dp, 10.dp, 0.dp))
        )
    }
}

val sections = listOf(R.string.all, R.string.football, R.string.apps, R.string.africa, R.string.hardware, R.string.ai, R.string.entertainment, R.string.startups, R.string.politics, R.string.tech)

val map: HashMap<Any?, String> = hashMapOf(
    R.string.all to "all",
    R.string.football to "football",
    R.string.apps to "apps",
    R.string.africa to "africa",
    R.string.hardware to "hardware",
    R.string.ai to "artificial-intelligence",
    R.string.entertainment to "media-entertainment",
    R.string.startups to "startups",
    R.string.politics to "politics",
    R.string.tech to "tech"
)

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