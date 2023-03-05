package com.example.newsapp_android.authentication

import android.app.Activity
import android.content.ContentValues
import android.widget.Toast
import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.example.newsapp_android.datastore.StoreUserEmail
import com.example.newsapp_android.datastore.StoreUserFullName
import com.example.newsapp_android.datastore.StoreUserPassword
import com.google.firebase.auth.FirebaseAuth

class Register {
    fun registerUser(email: String, fullName: String, password: String, confirmedPassword: String, auth: FirebaseAuth, OnSuccess: () -> Unit, OnFailure: () -> Unit, OnPasswordRejected: () -> Unit, ) {
        Log.d(ContentValues.TAG, "triggered..")
        if (password == confirmedPassword && password.length > 8) {
            var authenticate = auth.createUserWithEmailAndPassword(
                email, password
            )
            Thread.sleep(5_000)
            if (authenticate.isSuccessful) {
                var result = authenticate.result
                Log.d(ContentValues.TAG, "auth successful..")

                OnSuccess()
            } else {
                Log.d(ContentValues.TAG, "auth failed..")
                OnFailure()
            }
        }

        else if (fullName == "" || fullName == null ) {
            OnFailure()
        }
        else {
            OnPasswordRejected()
        }
    }
}