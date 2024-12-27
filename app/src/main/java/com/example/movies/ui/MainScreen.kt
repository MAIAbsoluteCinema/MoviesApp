package com.example.movies.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movies.model.Movie
import com.example.movies.viewmodel.MovieViewModel

@Composable
fun MainScreen(navController: NavController, movieViewModel: MovieViewModel = viewModel()) {
    val movies = movieViewModel.getMovies()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(movies) { movie ->
            MovieItem(movie) {
                navController.navigate("movie_detail/${movie.title}")
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Text(text = movie.title, style = MaterialTheme.typography.titleMedium)
        Text(text = "Рейтинг: ${movie.rating}", style = MaterialTheme.typography.bodyMedium)
    }
}