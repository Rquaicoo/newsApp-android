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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.example.newsapp_android.components.NewsComponent
import com.example.newsapp_android.components.SectionComponent
import com.example.newsapp_android.components.TrendingItemComponent
import com.example.newsapp_android.dataclasses.PostsModel
import com.example.newsapp_android.interfaces.PostsApi
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme
import com.example.newsapp_android.utilities.openLink
import kotlinx.coroutines.launch
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
        scope.launch {
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
                            .height(70.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(com.example.newsapp_android.components.sections) { item ->
                            SectionComponent(sectionName = stringResource(id = item))
                        }
                    }

                    /*Loading spinner*/
                    if(postsLoading && posts == null) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.CenterHorizontally),
                            color = Color(0xF6, 0x76, 0x00)
                        )
                    }

                    /*trending posts*/
                    if (!postsLoading && posts != null) {
                        Text(
                            "Trending",
                            lineHeight = 16.sp,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.arial)),
                            color = Color.Black,
                            modifier = Modifier.padding(top = 5.dp, bottom = 20.dp)
                        )

                        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(posts!!) { post ->
                                TrendingItemComponent(post = post, context = context)
                            }
                        }
                    }

                    /*user feed*/
                    if (!postsLoading && posts != null) {
                        LazyColumn(modifier = Modifier.height(500.dp)) {
                            items(posts!!) { post ->
                                NewsComponent(post = post, context = context)
                            }
                        }
                    }


                    /*end of user feed*/
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