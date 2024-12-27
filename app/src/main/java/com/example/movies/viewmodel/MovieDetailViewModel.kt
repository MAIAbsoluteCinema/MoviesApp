package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movies.model.MovieDetail

class MovieDetailViewModel : ViewModel() {

    // Моковые данные, которые позже будут заменены данными из бэкенда
    private val mockMovieDetails = mapOf(
        "Титаник" to MovieDetail(
            title = "Титаник",
            description = "Эпическая история любви и трагедии на фоне катастрофы мирового масштаба.",
            production = "США",
            language = "Английский",
            duration = "194 мин",
            genres = listOf("Мелодрама", "Исторический")
        ),
        "Аватар" to MovieDetail(
            title = "Аватар",
            description = "Фантастическая эпопея о борьбе за независимость коренного населения от разрушительного присутствия человечества.",
            production = "США",
            language = "Английский",
            duration = "162 мин",
            genres = listOf("Фантастика", "Приключения")
        ),
        "Матрица" to MovieDetail(
            title = "Матрица",
            description = "Захватывающая научно-фантастическая сага о мире иллюзий и борьбе за истину.",
            production = "США",
            language = "Английский",
            duration = "136 мин",
            genres = listOf("Фантастика", "Боевик")
        )
    )

    fun getMovieDetail(title: String): MovieDetail? {
        // Здесь позже будет запрос к бэкенду
        return mockMovieDetails[title]
    }
}