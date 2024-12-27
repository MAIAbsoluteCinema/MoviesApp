package com.example.movies.data.local

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean("is_logged_in", false)
        set(value) = sharedPreferences.edit().putBoolean("is_logged_in", value).apply()
}