package com.example.newsapp_android.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserEmail(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userEmail")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
    }

    val getEmail: Flow<String> = context.dataStore.data
        .map {
            preferences ->
            preferences[USER_EMAIL_KEY] ?: ""
        }

    suspend fun saveEmail(email_address: String) {
        context.dataStore.edit {
                preferences ->
            preferences[USER_EMAIL_KEY] = email_address
        }
    }
}