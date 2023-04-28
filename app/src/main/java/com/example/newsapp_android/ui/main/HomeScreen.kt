package com.example.newsapp_android.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp_android.R
import com.example.newsapp_android.RetrofitClient
import com.example.newsapp_android.components.SectionComponent
import com.example.newsapp_android.dataclasses.PostsModel
import com.example.newsapp_android.interfaces.PostsApi
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme
import com.example.newsapp_android.utilities.openLink
import java.util.*


val sections = listOf(R.string.news, R.string.sports, R.string.politics, R.string.tech)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var postsLoading by remember { mutableStateOf(false) }
    val trendingPostsLoading by remember { mutableStateOf(false) }
    var posts: List<PostsModel>? = null

    LaunchedEffect(Unit) {
        postsLoading = true
        posts = SendRequest(
            OnSuccess = {
            postsLoading= false
                        },
            OnFailure = {
                postsLoading = false
                        }
        )

        Log.d("Posts", posts.toString())
    }

    Surface(modifier = modifier.background(color = Color(0xF1, 0xF5, 0xF7))) {
        Column(modifier = Modifier.background(color = Color(0xF1, 0xF5, 0xF7))) {
            Column(
                modifier = Modifier
                    .background(color = Color(0xF1, 0xF5, 0xF7))
                    .padding(horizontal = 20.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                /*Greeting Section*/
                Column(modifier = Modifier) {
                    Row(
                        modifier = Modifier.padding(3.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Good morning.",
                            color = Color(0xB3, 0xB3, 0xB3),
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.arial)),
                            fontSize = 20.sp, lineHeight = 27.sp,
                            modifier = Modifier.padding(top = 20.dp, bottom = 3.dp)
                        )

                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            painter = painterResource(id = R.drawable.bell_pin),
                            contentDescription = "Notifications",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(29.dp)
                                .width(29.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color(0xB3, 0xB3, 0xB3),
                                    shape = CircleShape
                                )
                                .padding(5.dp)
                        )
                    }

                    Text(
                        "Russell Q.",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.arial)),
                        fontSize = 20.sp, lineHeight = 27.sp,
                        modifier = Modifier.padding(top = 1.dp, bottom = 1.dp)
                    )

                    LazyRow(
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(com.example.newsapp_android.components.sections) { item ->
                            SectionComponent(sectionName = stringResource(id = item))
                        }
                    }

                    /*Loading spinner*/
                    if(postsLoading && posts == null) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(30.dp)
                                .align(Alignment.CenterHorizontally),
                            color = Color(0xF6, 0x76, 0x00)
                        )
                    }

                    /*sample post*/
                    if (!postsLoading && posts != null) {
                        for (post in posts!!) {
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
                                        modifier = Modifier.padding(
                                            horizontal = 5.dp,
                                            vertical = 1.dp
                                        )
                                    )

                                    Row(modifier = Modifier) {
                                        Spacer(Modifier.weight(1f))

                                        Text(
                                            "20 hours ago",
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
                    }


                    /*end of sample post*/

                    Text(
                        "Trending",
                        lineHeight = 16.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.arial)),
                        color = Color.Black,
                        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
                    )
                    
                    /*Sample Trending Post*/
                    
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                            .height(129.dp)
                    ) {
                            Column(modifier = Modifier.width(200.dp)) {
                                    Row(modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)) {
                                        Image(
                                            painter = painterResource(id = R.drawable.bbc),
                                            contentDescription = null,
                                            contentScale = ContentScale.FillHeight,
                                            modifier = Modifier
                                                .padding(top = 4.dp, bottom = 1.dp)
                                                .width(14.dp)
                                                .height(14.dp)
                                                .clip(shape = CircleShape)

                                        )

                                        Text(
                                            "BBC News",
                                            lineHeight = 15.sp,
                                            fontStyle = FontStyle.Normal,
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = FontFamily(Font(R.font.arial)),
                                            color = Color(0x4E, 0x4E, 0x4E),
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 1.dp)
                                        )
                                    }

                                Text(
                                    "Facebook Annouces Meta with Virtual Reality and So...",
                                    lineHeight = 15.sp,
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily(Font(R.font.arial)),
                                    color = Color(0x4E, 0x4E, 0x4E),
                                    modifier = Modifier.padding(horizontal = 15.dp)

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
                                        "Yesterday",
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
                            painter = painterResource(id = R.drawable.zuck),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(102.dp)
                                .fillMaxHeight()
                                .clip(shape = RoundedCornerShape(0.dp, 10.dp, 10.dp, 0.dp))
                        )
                    }
                }
            }
        }
    }
}

suspend fun SendRequest(OnSuccess: () -> Unit, OnFailure: () -> Unit): List<PostsModel>? {
    val retrofit = RetrofitClient.getInstance()

    val api = retrofit.create(PostsApi::class.java)

    return try {
        val response = api.getUserFeed()

        if (response.isSuccessful){
            OnSuccess()
            response.body()
        }

        else {
            OnFailure()
            null
        }
    }

    catch (ex: Exception) {
        OnFailure()
        null
    }
}


@Preview(widthDp = 360, heightDp = 740, showBackground = true)
@Composable
fun HomePreview() {
    NewsAppandroidTheme {
        HomeScreen(modifier = Modifier)
    }
}