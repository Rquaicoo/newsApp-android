package com.example.newsapp_android.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.newsapp_android.authentication.Register
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserPassword(private val context: Context) {
        companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userPassword")
        val USER_PASSWORD_KEY = stringPreferencesKey("user_password")
    }

    val getPassword: Flow<String> = context.dataStore.data
        .map {
                preferences ->
            preferences[USER_PASSWORD_KEY] ?: ""
        }

    suspend fun savePassword(password: String) {
        context.dataStore.edit {
                preferences ->
            preferences[USER_PASSWORD_KEY] = password
        }
    }
}