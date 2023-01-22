package com.example.newsapp_android.ui.authentication

import android.content.ContentValues
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp_android.R
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme
import com.example.newsapp_android.authentication.Register
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(modifier: Modifier = Modifier, auth: FirebaseAuth = Firebase.auth, OnRegisterSuccess: () -> Unit = {}) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmedPassword by remember { mutableStateOf("") }


    Surface(modifier = modifier.background(color = Color.White)) {
        Column( modifier = Modifier.background(color = Color.White)) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .background(color = Color.White)
            ) {
                var context = LocalContext.current
                Text(
                    "Create an account",
                    color = Color(0x00, 0x27, 0x54),
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontSize = 24.sp, letterSpacing = 3.sp, lineHeight = 36.sp,
                    modifier = Modifier.padding(top = 60.dp, bottom = 5.dp)
                )

                Text(
                    "Enter your email and password, this would take less than a minute",
                    color = Color(0xB3, 0xB3, 0xB3),
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily(Font(R.font.arial)),
                    fontSize = 15.sp, lineHeight = 17.sp,
                    modifier = Modifier

                )

                Spacer(modifier = Modifier.height(30.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it.trim() },
                    placeholder = {
                        Text(
                            "Enter email here...", fontSize = 15.sp,
                            lineHeight = 17.sp,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily(Font(R.font.arial)),
                            color = Color(0xB3, 0xB3, 0xB3)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                        .background(Color(0xF1, 0xF5, 0xF7), shape = RoundedCornerShape(10.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xF1, 0xF5, 0xF7),
                        unfocusedBorderColor = Color(0xF1, 0xF5, 0xF7),
                        textColor = Color.Black
                    ),
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = password,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { password = it.trim() },
                    placeholder = {
                        Text(
                            "Password", fontSize = 15.sp,
                            lineHeight = 17.sp,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily(Font(R.font.arial)),
                            color = Color(0xB3, 0xB3, 0xB3)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                        .background(Color(0xF1, 0xF5, 0xF7), shape = RoundedCornerShape(10.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xF1, 0xF5, 0xF7),
                        unfocusedBorderColor = Color(0xF1, 0xF5, 0xF7),
                        textColor = Color.Black
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = confirmedPassword,
                    onValueChange = { confirmedPassword = it.trim() },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    placeholder = {
                        Text(
                            "Confirm password", fontSize = 15.sp,
                            lineHeight = 17.sp,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily(Font(R.font.arial)),
                            color = Color(0xB3, 0xB3, 0xB3)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                        .background(Color(0xF1, 0xF5, 0xF7), shape = RoundedCornerShape(10.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xF1, 0xF5, 0xF7),
                        unfocusedBorderColor = Color(0xF1, 0xF5, 0xF7),
                        textColor = Color.Black
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {
                        var register = Register()
                              register.registerUser(
                                  email,
                                  password,
                                  confirmedPassword,
                                  auth,
                                  OnSuccess = OnRegisterSuccess,
                                  OnFailure = { Toast.makeText(context, "Sign up failed", Toast.LENGTH_LONG).show() },
                                  OnPasswordRejected = { Toast.makeText(context, "Passwords are weak or do not match", Toast.LENGTH_LONG).show() }
                              )
                    },
                    modifier = Modifier
                        .padding(vertical = 2.dp, horizontal = 2.dp)
                        .fillMaxWidth()
                        .height(61.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xF6, 0x76, 0x00))
                ) {
                    Text(
                        "Create an Account",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.arial)),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Already a member?",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.arial)),
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Normal,
                    )
                    Text(
                        "Login",
                        color = Color(0xF6, 0x76, 0x00),
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.arial)),
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Divider(
                        color = Color(0xD4, 0xD4, 0xD4),
                        thickness = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth(0.32f)
                            .padding(vertical = 10.dp)
                    )

                    Text(
                        "Or Sign up with",
                        color = Color.Black,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.arial)),
                        lineHeight = 2.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )

                    Divider(
                        color = Color(0xD4, 0xD4, 0xD4),
                        thickness = 1.dp,
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                    )
                }
                
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp).height(100.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = buttonColors(Color(0x00, 0x27, 0x54)),
                        modifier = Modifier.height(57.dp).width(172.dp),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Row(modifier = Modifier, horizontalArrangement = Arrangement.SpaceBetween) {
                            Image(painter = painterResource(R.drawable.facebook), contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(21.dp)
                                    .width(12.dp)
                            )

                            Text(
                                "Facebook",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.arial)),
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(horizontal = 15.dp)
                            )
                        }
                    }

                    Button(
                        onClick = { /*TODO*/ },
                        colors = buttonColors(Color(0xFF, 0xFF, 0xFF)),
                        modifier = Modifier.height(57.dp).width(172.dp),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color(0xE8, 0xE8, 0xE8))
                    ) {
                        Row(modifier = Modifier, horizontalArrangement = Arrangement.Center) {
                            Image(painter = painterResource(R.drawable.google), contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(31.dp)
                                    .width(16.dp)
                            )

                            Text(
                                "Google",
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.arial)),
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(widthDp = 500, heightDp = 640, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun RegisterPreview() {
    NewsAppandroidTheme {
        Register(modifier = Modifier, auth = Firebase.auth)
    }
}