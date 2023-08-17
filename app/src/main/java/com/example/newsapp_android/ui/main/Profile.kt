package com.example.newsapp_android.ui.main

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp_android.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, OnSignOut: () -> Unit) {

    val auth: FirebaseAuth = Firebase.auth
    Log.d("UID", auth.currentUser?.uid.toString())

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)

        ) {
            Text(
                "Profile",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 27.sp,
                fontFamily = FontFamily(Font(R.font.arial)),
                lineHeight = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top=30.dp, bottom = 30.dp).fillMaxWidth()
            )


            Column(modifier = Modifier
                .height(10.dp)
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxWidth()
            ) {

            }

            Column(modifier = Modifier
                .heightIn(min = 63.dp)
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AccountDetails()
            }

            Spacer(modifier = Modifier.padding(vertical = 15.dp))

            Column(modifier = Modifier
                .heightIn(min = 63.dp)
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OptionsSection()
            }

            Spacer(modifier = Modifier.padding(vertical = 15.dp))

            Column(modifier = Modifier
                .heightIn(min = 100.dp)
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AccountSection()
            }
        }

}

@Composable
fun AccountDetails(modifier: Modifier = Modifier) {

        Row(modifier = Modifier.fillMaxWidth(fraction = 0.9f), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.profile_thick),
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = "Profile icon",
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            )

            Column(modifier = Modifier) {
                Text(
                    Firebase.auth.currentUser?.displayName.toString(),
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.arial)),
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.W900,
                )

                Text(
                    Firebase.auth.currentUser?.email.toString(),
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.arial)),
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Normal,
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.next),
                tint = MaterialTheme.colorScheme.secondary,
                contentDescription = "Open",
                modifier = Modifier
                    .height(40.dp)
                    .padding(start = 0.dp, top = 0.dp, end = 8.dp, bottom = 0.dp)
            )
        }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun OptionsSection(modifier: Modifier = Modifier) {

    var switchOn by remember { mutableStateOf(false) }

    Column(modifier.fillMaxWidth(fraction = 0.9f)) {
        Text(
            "Options",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.arial)),
            lineHeight = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(top=5.dp, bottom = 5.dp)
        )

        Divider(
            color = Color(0xD7, 0xD7, 0xD7),
            thickness = 1.dp,
            modifier = Modifier
                .padding(vertical = 10.dp)
        )

        Row(modifier = Modifier) {
            Text(
                "Notifications",
                color = Color(0x95, 0x95, 0x95),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.arial)),
                lineHeight = 20.sp,
                fontWeight = FontWeight.ExtraBold,
            )

            Spacer(modifier = Modifier.weight(1f))

            Switch(
                checked = switchOn,
                onCheckedChange = { switchOn_ ->
                    switchOn = switchOn_
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color(0xF6, 0x76, 0x00),
                    checkedTrackColor = Color(0xF2, 0xF2, 0xF2),
                    uncheckedTrackColor = Color(0xF2, 0xF2, 0xF2),
                    uncheckedThumbColor = Color(0x94, 0x94, 0x94)
                ),
                modifier = Modifier
                    .scale(0.6f)
                    .height(10.dp)
            )

        }

        Row(modifier = Modifier.padding(vertical = 10.dp)) {
            Text(
                "Language",
                color = Color(0x95, 0x95, 0x95),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.arial)),
                lineHeight = 20.sp,
                fontWeight = FontWeight.ExtraBold,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "English",
                color = Color(0x95, 0x95, 0x95),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.arial)),
                lineHeight = 20.sp,
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}

@Composable
fun AccountSection(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth(fraction = 0.9f)) {
        Text(
            "Account",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.arial)),
            lineHeight = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(top=5.dp, bottom = 5.dp)
        )

        Divider(
            color = Color(0xD7, 0xD7, 0xD7),
            thickness = 1.dp,
            modifier = Modifier
                .padding(vertical = 10.dp)
        )

        Row(modifier = Modifier) {
            Text(
                "Update Preferences",
                color = Color(0x95, 0x95, 0x95),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.arial)),
                lineHeight = 20.sp,
                fontWeight = FontWeight.ExtraBold,
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.next),
                contentDescription = "Open",
                tint = Color(0x95, 0x95, 0x95),
                modifier = Modifier
                    .height(40.dp)
                    .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
            )
        }

        Row(modifier = Modifier) {
            Text(
                "Privacy Policy",
                color = Color(0x95, 0x95, 0x95),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.arial)),
                lineHeight = 20.sp,
                fontWeight = FontWeight.ExtraBold,
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.next),
                contentDescription = "Open",
                tint = Color(0x95, 0x95, 0x95),
                modifier = Modifier
                    .height(40.dp)
                    .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)

            )
        }

        Row(modifier = Modifier) {
            Text(
                "Terms of Service",
                color = Color(0x95, 0x95, 0x95),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.arial)),
                lineHeight = 20.sp,
                fontWeight = FontWeight.ExtraBold,
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.next),
                contentDescription = "Open",
                tint = Color(0x95, 0x95, 0x95),
                modifier = Modifier
                    .height(40.dp)
                    .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)

            )
        }

        Button(
            onClick = {
                Firebase.auth.signOut()
            },
            colors = ButtonDefaults.buttonColors(Color(0xEE, 0xEE, 0xEE)),
            modifier = Modifier
                .height(35.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(3.dp),
            border = BorderStroke(1.dp, Color(0xE8, 0xE8, 0xE8)),
        ) {
            Text(
                "Logout",
                color = Color(0x02, 0x38, 0x76),
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.arial)),
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 0.dp, vertical = 0.dp )
            )
        }

        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(Color(0xEE, 0xEE, 0xEE)),
            modifier = Modifier
                .padding(vertical = 6.dp)
                .height(35.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(3.dp),
            border = BorderStroke(1.dp, Color(0xEA, 0xEA, 0xEA))
        ) {
            Text(
                "Delete Account",
                color = Color(0xF6, 0x76, 0x00),
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.arial)),
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 0.dp, vertical = 0.dp )
            )
        }
    }
}

@Preview(widthDp = 360, heightDp = 70, showBackground = true)
@Composable
fun AccountDetailsPreview() {
    AccountDetails()
}

@Preview(widthDp = 360, heightDp = 110, showBackground = true)
@Composable
fun OptionsSectionPreview() {
    OptionsSection()
}

@Preview(widthDp = 360, heightDp = 220, showBackground = true)
@Composable
fun AccountSectionPreview() {
    AccountSection()
}

@Preview(widthDp = 360, heightDp = 550, showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(OnSignOut = { })
}