package com.example.movies.model

data class MovieDetail(
    val id: Long,
    val title: String,
    val overview: String,
    val production_countries: String,
    val spoken_languages: String,
    val runtime: String,
    val genres: String
)