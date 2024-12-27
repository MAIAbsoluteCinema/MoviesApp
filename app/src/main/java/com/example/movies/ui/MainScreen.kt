package com.example.movies.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movies.model.Movie
import com.example.movies.viewmodel.MovieViewModel
import com.example.movies.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    movieViewModel: MovieViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movies") },
                actions = {
                    IconButton(onClick = {
                        userViewModel.logoutUser()
                        navController.navigate("login") {
                            popUpTo("main") { inclusive = true }
                        }
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Logout")
                    }
                }
            )
        },

        content = { paddingValues ->
            val movies = movieViewModel.getMovies()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                items(movies) { movie ->
                    MovieItem(movie) {
                        navController.navigate("movie_detail/${movie.title}")
                    }
                }
            }
        }
    )
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