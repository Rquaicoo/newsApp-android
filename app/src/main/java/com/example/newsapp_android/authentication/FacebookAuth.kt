package com.example.newsapp_android.authentication

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.ui.platform.LocalContext
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class FacebookAuth(private val OnSuccess: () -> Unit = { }, private val OnFailure: () -> Unit = { }) : Activity() {



    private lateinit var auth: FirebaseAuth

    private lateinit var callbackManager: CallbackManager
    private lateinit var facebookLoginButton: LoginButton


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        callbackManager = CallbackManager.Factory.create()

        facebookLoginButton = LoginButton(this)

        facebookLoginButton.setPermissions("email", "public_profile")
        facebookLoginButton.registerCallback(callbackManager, object: FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                if (result != null) {
                    handleAccessToken(OnSuccess, OnFailure, result.accessToken, auth)
                }
                else {
                    OnFailure()
                }
            }

            override fun onCancel() {
                Log.d(TAG, "Request cancelled")
            }

            override fun onError(error: FacebookException) {
                OnFailure()
            }
        })

    }

    public override fun onStart() {
        super.onStart()

        val user = auth.currentUser
        updateUI(user)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    fun handleAccessToken(OnLoginSuccess: () -> Unit, OnLoginFailure: () -> Unit, token: AccessToken, auth: FirebaseAuth) {
        Log.d(TAG, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)

        auth.signInWithCredential(credential)
            .addOnSuccessListener {result ->

                Log.d("Login Success", "Facebook auth successful.. ${result.user?.email}")
                OnSuccess()
            }

            .addOnFailureListener {result ->

                Log.d("Login Failure", "Facebook auth failed.. ${result.message}")
                OnFailure()
            }

    }

    private fun updateUI(user: FirebaseUser?) {

    }
    companion object {
        private const val TAG = "FacebookLogin"
    }
}