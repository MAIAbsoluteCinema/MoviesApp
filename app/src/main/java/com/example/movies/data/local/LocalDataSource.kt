package com.example.movies.data.local

class LocalDataSource(private val preferencesManager: PreferencesManager) {

    fun saveAuthState(isLoggedIn: Boolean) {
        preferencesManager.isLoggedIn = isLoggedIn
    }

    fun isUserLoggedIn(): Boolean {
        return preferencesManager.isLoggedIn
    }
}