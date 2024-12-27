package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movies.model.Movie

class MovieViewModel : ViewModel() {
    fun getMovies(): List<Movie> {
        return listOf( // Заглушка данных о фильмах
            Movie("Титаник", 4.5),
            Movie("Аватар", 4.7),
            Movie("Матрица", 4.9)
        )
    }
}
