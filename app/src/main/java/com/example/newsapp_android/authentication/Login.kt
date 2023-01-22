package com.example.newsapp_android.authentication

import android.app.Activity
import android.content.ContentValues
import android.util.Log
import com.example.newsapp_android.Register
import com.google.firebase.auth.FirebaseAuth

class Login {

    public fun LoginUser(email: String, password: String, auth: FirebaseAuth, OnSuccess: () -> Unit, OnFailure: () -> Unit) {
        var authenticate = auth.signInWithEmailAndPassword(
            email, password
        )

        Thread.sleep(5_000)

         if (authenticate.isSuccessful) {
             var result = authenticate.result
             Log.d(ContentValues.TAG, "auth successful..")
             OnSuccess()
         }
        else{
             Log.d(ContentValues.TAG, "auth failed..")
             OnFailure()
         }
    }
}