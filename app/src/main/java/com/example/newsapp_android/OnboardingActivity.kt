package com.example.newsapp_android


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme

@Composable
fun OnboardingComponent(modifier: Modifier = Modifier, OnRegisterClick : () -> Unit = {}, OnLoginClick : () -> Unit = {}) {
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x1E, 0x1E, 0x1E))
        ) {
            Image(
                painter = painterResource(id = R.drawable.landing_img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )

            Text(
                "Get Started",
                color = Color.White, fontSize = 15.sp, fontFamily = FontFamily(Font(R.font.arial)),
                fontWeight = FontWeight.Normal, modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)
            )

            Text(
                "Stay up to date with news from  all over the world",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontSize = 30.sp, letterSpacing = 3.sp, lineHeight = 30.sp,
                modifier = Modifier
                    .width(339.dp)
                    .height(160.dp)
                    .padding(horizontal = 20.dp)

            )

            Text(
                "the easiest way to stay updated",
                color = Color.White, fontSize = 15.sp, fontFamily = FontFamily(Font(R.font.arial)),
                fontWeight = FontWeight.Normal, modifier = Modifier.padding(vertical = 2.dp, horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(
                    onClick = {
                        OnRegisterClick()
                    },
                    modifier = Modifier
                        .height(58.dp)
                        .width(168.dp)
                        .padding(vertical = 2.dp, horizontal = 12.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = buttonColors(Color(0xF6, 0x76, 0x00))
                ) {
                    Text(
                        "Register",
                        color = Color.White, fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.arial)),
                        fontWeight = FontWeight.Normal, textAlign = TextAlign.Center, modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp)
                    )
                }

                Button(
                    onClick = {
                              OnLoginClick()
                    },
                    modifier = Modifier
                        .height(58.dp)
                        .width(168.dp)
                        .padding(vertical = 2.dp, horizontal = 12.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = buttonColors(Color.White)
                ) {
                    Text(
                        "Login",
                        color = Color(0x00, 0x27,0x54), fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.arial)),
                        fontWeight = FontWeight.Normal, textAlign = TextAlign.Center, modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp)
                    )
                }
            }
        }
    }

}


@Preview(widthDp = 360, heightDp = 640, showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppandroidTheme {
        OnboardingComponent()
    }
}