package com.example.movies.data.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("/register")
    fun registerUser(@Body request: RegisterRequest): Call<Void>

    @Headers("Content-Type: application/json")
    @POST("/login")
    fun loginUser(@Body request: LoginRequest): Call<Void>
}