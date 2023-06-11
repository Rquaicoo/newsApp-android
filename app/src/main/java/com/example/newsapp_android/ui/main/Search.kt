package com.example.newsapp_android.ui.main

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp_android.InfluxnewsTheme
import com.example.newsapp_android.R
import com.example.newsapp_android.RetrofitClient
import com.example.newsapp_android.components.TrendingItemComponent
import com.example.newsapp_android.dataclasses.PostsModel
import com.example.newsapp_android.interfaces.PostByCategoryAPI
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(modifier: Modifier = Modifier) {

    var query by remember { mutableStateOf("") }
    var queryResults: List<PostsModel>? = null
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var resultsLoading by remember { mutableStateOf(false) }

    Surface(modifier = modifier
        .background(MaterialTheme.colorScheme.primary)
        .fillMaxSize()) {
        Row(modifier = Modifier, horizontalArrangement = Arrangement.Center) {
            OutlinedTextField(
                value = query,
                onValueChange = {
                    query = it.trim()

                    scope.launch {
                        resultsLoading = true

                        queryResults = SearchQuery(
                            query = it.trim(),
                            OnSuccess = {
                                Toast.makeText(context, "Results found!", Toast.LENGTH_SHORT).show()

                                resultsLoading = false
                            },
                            OnFailure = {
                                Toast.makeText(context, "Request failed!", Toast.LENGTH_SHORT).show()

                                resultsLoading = false
                            },
                            OnResponseBodyNull = {
                                Toast.makeText(context, "No results", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                                },
                placeholder = {
                    Text(
                        "Search...", fontSize = 15.sp,
                        lineHeight = 17.sp,
                        fontStyle = FontStyle.Normal,
                        fontFamily = FontFamily(Font(R.font.arial)),
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                shape = RoundedCornerShape(10.dp),
                modifier =
                Modifier
                    .height(90.dp)
                    .padding(horizontal = 5.dp, vertical = 20.dp)
                    .fillMaxWidth(0.9f)

                    .background(
                        MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(10.dp)
                    ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.secondary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                    textColor = MaterialTheme.colorScheme.secondary,
                ),
            )
        }

        Box(modifier = Modifier) {
            /*Loading spinner*/
            if(resultsLoading && queryResults == null) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center),
                    color = Color(0xF6, 0x76, 0x00)
                )
            }
                /*search results*/
                if (!resultsLoading && queryResults != null) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(0.9f).padding(top = 100.dp).align(Alignment.TopCenter),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        items(queryResults!!) { result ->
                            TrendingItemComponent(post = result, context = context,)
                        }
                }

            }
        }


    }
}

suspend fun SearchQuery(query:String, OnSuccess: () -> Unit, OnFailure: () -> Unit, OnResponseBodyNull: () -> Unit): List<PostsModel>? {
    val retrofit = RetrofitClient.getInstance()

    val api = retrofit.create(PostByCategoryAPI::class.java)

    return try {
        val response = api.getFeedByParam(url = "v1/news/news/?query=${query}")

        if (response.isSuccessful){
            OnSuccess()

            if (response.body() == null ) {
                OnResponseBodyNull()
            }
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
fun SearchPreview() {
    InfluxnewsTheme {
        SearchScreen(modifier = Modifier)
    }
}