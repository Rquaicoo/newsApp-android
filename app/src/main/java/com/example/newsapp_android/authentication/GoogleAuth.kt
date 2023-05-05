package com.example.newsapp_android.authentication


import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class GoogleAuth {

    suspend fun signIn(context: Context, launcher: ActivityResultLauncher<IntentSenderRequest>, client_id: String) {
        val oneTapClient = Identity.getSignInClient(context)
        val signUpRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(client_id)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()

        Log.d(TAG, "One tap build completed")

        try {
            val result = oneTapClient.beginSignIn(signUpRequest).await()

            //construct required intent sender by launcher
            val intentSenderRequest = IntentSenderRequest.Builder(result.pendingIntent).build()
            launcher.launch(intentSenderRequest)

        }

        catch (e: Exception) {
            val e = e.message.toString()
            Log.d(TAG, "Exception occurred: $e")
        }
    }

    fun authenticateWithFirebase(idToken: String, context: Context, auth: FirebaseAuth, OnSuccess: () -> Unit, OnFailure: () -> Unit) {
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(firebaseCredential)
            .addOnSuccessListener {result ->

                Log.d("Login Success", "Google auth successful.. ${result.user?.email}")
                OnSuccess()
            }

            .addOnFailureListener {result ->

                Log.d("Login Failure", "Googgle auth failed.. ${result.message}")
                OnFailure()
            }
    }
}