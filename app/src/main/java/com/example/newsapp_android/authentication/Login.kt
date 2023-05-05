package com.example.newsapp_android.authentication

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.newsapp_android.Register
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Login {

    public fun LoginUser(
        email: String,
        password: String,
        auth: FirebaseAuth,
        OnSuccess: () -> Unit,
        OnFailure: () -> Unit
    ) {
        if (email == "" && password == "") {
            OnFailure()
            return
        }

        auth.signInWithEmailAndPassword(
            email, password
        )
            .addOnSuccessListener {result ->

                Log.d("Login Success", "auth successful.. ${result.user?.email}")
                OnSuccess()
            }

            .addOnFailureListener {result ->

                Log.d("Login Failure", "auth failed.. ${result.message}")
                OnFailure()
            }
    }
}

