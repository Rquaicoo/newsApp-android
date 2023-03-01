package com.example.newsapp_android.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserFullName (private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userFullName")
        val USER_FULLNAME_KEY = stringPreferencesKey("user_full_name")
    }

    val getFullName: Flow<String> = context.dataStore.data
        .map {
                preferences ->
            preferences[USER_FULLNAME_KEY] ?: ""
        }

    suspend fun saveFullName(fullName: String) {
        context.dataStore.edit {
                preferences ->
            preferences[USER_FULLNAME_KEY] = fullName
        }
    }
}