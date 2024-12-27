package com.example.movies.model

data class MovieDetail(
    val title: String,
    val description: String,
    val production: String,
    val language: String,
    val duration: String,
    val genres: List<String>
)