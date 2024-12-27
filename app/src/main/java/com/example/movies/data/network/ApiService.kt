package com.example.movies.data.network

import com.example.movies.model.Movie
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService8080 {
    @Headers("Content-Type: application/json")
    @POST("/register")
    fun registerUser(@Body request: RegisterRequest): Call<Void>

    @Headers("Content-Type: application/json")
    @POST("/login")
    fun loginUser(@Body request: LoginRequest): Call<Void>
}

interface ApiService8082 {
    @GET("/api/v1/films/")
    fun getFilms(@Query("films") numberOfFilms: Int): Call<List<Movie>>
}