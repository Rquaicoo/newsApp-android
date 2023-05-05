package com.example.newsapp_android.ui.authentication

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultRegistryOwner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsapp_android.R
import com.example.newsapp_android.authentication.GoogleAuth
import com.example.newsapp_android.ui.theme.NewsAppandroidTheme
import com.example.newsapp_android.authentication.Register
import com.example.newsapp_android.datastore.StoreUserEmail
import com.example.newsapp_android.datastore.StoreUserFullName
import com.example.newsapp_android.datastore.StoreUserPassword
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(modifier: Modifier = Modifier, auth: FirebaseAuth = Firebase.auth, OnRegisterSuccess: () -> Unit = {}, NavigateToLogin: () -> Unit = {}) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    var confirmedPassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    val googleAuth = GoogleAuth()

    val fullNameDataStore = StoreUserFullName(context)

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) {
        result ->
        if(result.resultCode != Activity.RESULT_OK) {
            if (result.data?.action == ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST) {
                
            }
            return@rememberLauncherForActivityResult
        }
        
        val oneTapClient = Identity.getSignInClient(context)
        val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
        val idToken = credential.googleIdToken
        
        if (idToken != null) {
            //authenticate with backend
            googleAuth.authenticateWithFirebase(
                idToken = idToken,
                context = context, auth = auth,
                OnSuccess = { OnRegisterSuccess() },
                OnFailure = { Toast.makeText(context, "Sign up failed", Toast.LENGTH_LONG).show() }
            )
        }
        else {
            Log.d(TAG, "Token is null")
        }
                
    }

    val callbackManager = CallbackManager.Factory.create()

    LoginManager.getInstance().registerCallback(callbackManager, object: FacebookCallback<LoginResult> {
        override fun onSuccess(result: LoginResult) {
            if (result != null) {
                Log.d(TAG, "handleFacebookAccessToken:${result.accessToken}")

                val credential = FacebookAuthProvider.getCredential(result.accessToken.token)

                 auth.signInWithCredential(credential)
                    .addOnSuccessListener {result ->

                        Log.d("Login Success", "auth successful.. ${result.user?.email}")
                        OnRegisterSuccess()
                    }

                    .addOnFailureListener {result ->

                        Log.d("Facebook Login Failure", "auth failed.. ${result.message}")
                        Toast.makeText(context, result.message.toString(), Toast.LENGTH_LONG).show()
                    }
            }
            else {
                Toast.makeText(context, "Sign up failed", Toast.LENGTH_LONG).show()
            }
        }

        override fun onCancel() {
            Log.d(TAG, "Request cancelled")
        }

        override fun onError(error: FacebookException) {
            Log.d(TAG, "FB error: $error")
            Toast.makeText(context, "Sign up failed", Toast.LENGTH_LONG).show()
        }
    })


    


    Surface(modifier = modifier.background(color = Color.White)) {
        Column( modifier = Modifier.background(color = Color.White)) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .background(color = Color.White)
            ) {
                val scope = rememberCoroutineScope()

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
                    modifier = Modifier
                        .fillMaxWidth()
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
                    onValueChange = { password = it },
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
                    modifier = Modifier
                        .fillMaxWidth()
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
                    onValueChange = { confirmedPassword = it },
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
                    modifier = Modifier
                        .fillMaxWidth()
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
                    value = fullname,
                    onValueChange = { fullname = it.trim() },
                    placeholder = {
                        Text(
                            "Full name", fontSize = 15.sp,
                            lineHeight = 17.sp,
                            fontStyle = FontStyle.Normal,
                            fontFamily = FontFamily(Font(R.font.arial)),
                            color = Color(0xB3, 0xB3, 0xB3)
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xF1, 0xF5, 0xF7), shape = RoundedCornerShape(10.dp)),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xF1, 0xF5, 0xF7),
                        unfocusedBorderColor = Color(0xF1, 0xF5, 0xF7),
                        textColor = Color.Black
                    ),
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {
                        var register = Register()
                              register.registerUser(
                                  email,
                                  fullname,
                                  password,
                                  confirmedPassword,
                                  auth,
                                  OnSuccess = {
                                      //save full name
                                      scope.launch {
                                          fullNameDataStore.saveFullName(fullname)
                                      }

                                      //invoke success function
                                      OnRegisterSuccess()
                                              },
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
                        modifier = Modifier.clickable {
                            NavigateToLogin()
                        }
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
                
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .height(100.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(
                        onClick = {
                            val permissions = listOf("email", "public_profile")
                            LoginManager.getInstance().logIn(context as ActivityResultRegistryOwner, callbackManager, permissions)


                            Log.d(TAG, "Facebook auth triggered")

                        },
                        colors = buttonColors(Color(0x00, 0x27, 0x54)),
                        modifier = Modifier
                            .height(57.dp)
                            .width(172.dp),
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
                        onClick = {

                            scope.launch {
                                googleAuth.signIn(context, launcher, "1001854651982-rpecr524dr4jujr1b8a229njnevnphn1.apps.googleusercontent.com")
                                Log.d(TAG, "Google auth triggered")
                            }
                        },
                        colors = buttonColors(Color(0xFF, 0xFF, 0xFF)),
                        modifier = Modifier
                            .height(57.dp)
                            .width(172.dp),
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