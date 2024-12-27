package com.example.movies.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.movies.data.local.LocalDataSource
import com.example.movies.data.local.PreferencesManager
import com.example.movies.data.network.LoginRequest
import com.example.movies.data.network.RegisterRequest
import com.example.movies.data.network.RetrofitInstance8080
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val localDataSource = LocalDataSource(PreferencesManager(application))

    fun registerUser(
        email: String, password: String, username: String, onResult: (Boolean) -> Unit
    ) {
        val request = RegisterRequest(email, password, username)
        val call = RetrofitInstance8080.api.registerUser(request)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    localDataSource.saveAuthState(true)
                    onResult(true)
                } else {
                    Log.e("UserViewModel", "Ошибка регистрации: ${response.errorBody()?.string()}")
                    onResult(false)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("UserViewModel", "Не удалось выполнить запрос: ${t.message}", t)
                onResult(false)
            }
        })
    }

    fun loginUser(email: String, password: String, onResult: (Boolean) -> Unit) {
        val request = LoginRequest(email, password)
        val call = RetrofitInstance8080.api.loginUser(request)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    localDataSource.saveAuthState(true)
                    onResult(true)
                } else {
                    Log.e("UserViewModel", "Ошибка авторизации: ${response.errorBody()?.string()}")
                    onResult(false)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("UserViewModel", "Не удалось выполнить запрос: ${t.message}", t)
                onResult(false)
            }
        })
    }


    fun isUserLoggedIn(): Boolean {
        return localDataSource.isUserLoggedIn()
    }

    fun logoutUser() {
        localDataSource.saveAuthState(false)
    }
}