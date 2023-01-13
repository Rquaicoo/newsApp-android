package com.example.newsapp_android.ui.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp_android.R
import com.example.newsapp_android.components.SectionComponent
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme


val sections = listOf(R.string.news, R.string.sports, R.string.politics, R.string.tech)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(modifier: Modifier = Modifier) {
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
                    Row(modifier = Modifier.padding(3.dp), horizontalArrangement = Arrangement.SpaceBetween) {
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

                    LazyRow(modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(com.example.newsapp_android.components.sections) {
                                item -> SectionComponent(sectionName = stringResource(id = item))
                        }
                    }
                    
                    /*sample post*/
                    Box(
                        modifier = Modifier
                            .height(329.dp)
                            .fillMaxWidth()

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.elon_twitter),
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
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
                                "Technology",
                                lineHeight = 17.sp,
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily(Font(R.font.arial)),
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 1.dp)
                            )
                        }



                        Column(modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .background(
                                color = Color(0xFF, 0xFF, 0xFF, 0x99),
                                shape = RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp)
                            )) {
                            Text(
                                "The New York Times",
                                lineHeight = 15.sp,
                                fontStyle = FontStyle.Normal,
                                fontFamily = FontFamily(Font(R.font.arial)),
                                color = Color(0x4E, 0x4E, 0x4E),
                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                            )

                            Text(
                                "Elon Musk Took Over a Struggling Business with Twitter and Has Quickly Made is Wo...",
                                lineHeight = 17.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.arial)),
                                color = Color.Black,
                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                            )

                            Row(modifier = Modifier) {
                                Spacer(Modifier.weight(1f))

                                Text(
                                    "20 hours ago",
                                    lineHeight = 17.sp,
                                    fontStyle = FontStyle.Normal,
                                    fontFamily = FontFamily(Font(R.font.arial)),
                                    color = Color.Black,
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                                )
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

@Preview(widthDp = 360, heightDp = 740, showBackground = true)
@Composable
fun HomePreview() {
    NewsAppandroidTheme {
        Home(modifier = Modifier)
    }
}