package com.example.newsapp_android.authentication

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp_android.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import android.util.Log
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class GoogleAuth : Activity() {

    private lateinit var oneTapClient: SignInClient
    private lateinit var signUpRequest: BeginSignInRequest

    private val REQ_ONE_TAP = 2
    private var showOneTapUI = true

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        oneTapClient = Identity.getSignInClient(this)
        signUpRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(getString(R.string.default_web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

        auth = Firebase.auth

        oneTapClient.beginSignIn(signUpRequest)
            .addOnSuccessListener(this) { result ->
                try {
                    startIntentSenderForResult(
                        result.pendingIntent.intentSender, REQ_ONE_TAP,
                        null, 0, 0, 0)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e(TAG, "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            .addOnFailureListener(this) { e ->
                // No Google Accounts found. Just continue presenting the signed-out UI.
                Log.d(TAG, e.localizedMessage)
            }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        updateUI(user)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            REQ_ONE_TAP -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken

                    when {
                        idToken != null -> {
                            Log.d(TAG, "Got ID token")

                            //exchange Google ID for Firebase credential and authenticate with credential

                            val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                            auth.signInWithCredential(firebaseCredential)
                                .addOnCompleteListener(this) {
                                    task ->

                                    if (task.isSuccessful) {
                                        Log.d(TAG, "signInWithCredential:success")
                                        val user = auth.currentUser
                                        updateUI(user)
                                    }

                                    else {
                                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                                        updateUI(null)
                                    }
                                }
                        }

                        else -> {
                            Log.d(TAG, "No id token")
                        }
                    }
                }
                catch (e: ApiException) {
                    Log.d(TAG, "API Exception: $e")
                }
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {

    }
}