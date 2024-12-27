package com.example.movies.model

data class Movie(
    val movieId: Long,
    val title: String,
    val vote_average: Double,
    val vote_count: Long,
)
