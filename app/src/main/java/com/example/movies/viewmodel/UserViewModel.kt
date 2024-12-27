package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    fun registerUser(email: String, password: String): Boolean {
        // Заглушка для процесса регистрации
        return true
    }

    fun loginUser(email: String, password: String): Boolean {
        return true  // Заглушка для авторизации
    }
}
