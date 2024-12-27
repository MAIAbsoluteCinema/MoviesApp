package com.example.movies.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance8080 {
    private const val BASE_URL_8080 = "http://10.0.2.2:8080"

    private val retrofit8080 by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_8080)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService8080 by lazy {
        retrofit8080.create(ApiService8080::class.java)
    }
}

object RetrofitInstance8082 {
    private const val BASE_URL_8082 = "http://10.0.2.2:8082"

    private val retrofit8082 by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_8082)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService8082 by lazy {
        retrofit8082.create(ApiService8082::class.java)
    }
}