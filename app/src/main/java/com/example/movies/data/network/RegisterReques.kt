package com.example.movies.data.network

data class RegisterRequest(
    val email: String,
    val password: String,
    val username: String
)