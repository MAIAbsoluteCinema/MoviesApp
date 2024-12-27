package com.example.movies.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movies.viewmodel.MovieDetailViewModel
import kotlin.math.round

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(filmId: Long, navController: NavController) {
    val viewModel: MovieDetailViewModel = viewModel()
    val movieDetail by viewModel.movieDetail.observeAsState()
    val errorMessage by viewModel.error.observeAsState()
    val context = LocalContext.current

    val prefs: SharedPreferences = context.getSharedPreferences("movie_prefs", Context.MODE_PRIVATE)
    var rawRating by remember { mutableFloatStateOf(prefs.getFloat("rating_$filmId", 0f)) }
    val roundedRating = remember(rawRating) { round(rawRating * 10) / 10 }

    LaunchedEffect(filmId) {
        viewModel.fetchMovieDetail(filmId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали фильма") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                when {
                    errorMessage != null -> {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Ошибка: $errorMessage",
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = { navController.popBackStack() }) {
                                Text("Назад")
                            }
                        }
                    }
                    movieDetail != null -> {
                        movieDetail?.let { detail ->
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = detail.title,
                                    style = MaterialTheme.typography.titleLarge,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Описание: ${detail.overview}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Производство: ${detail.production_countries}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Язык: ${detail.spoken_languages}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Длительность: ${detail.runtime}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Жанры: ${detail.genres}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "Оцените фильм: ${"%.1f".format(roundedRating)}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )

                                Slider(
                                    value = rawRating,
                                    onValueChange = { rawRating = it },
                                    onValueChangeFinished = {
                                        val updatedRoundedRating = round(rawRating * 10) / 10
                                        prefs.edit().putFloat("rating_$filmId", updatedRoundedRating).apply()
                                    },
                                    valueRange = 0f..5f,
                                    steps = 49
                                )
                            }

                        Spacer(modifier = Modifier.height(36.dp))

                            Button(
                                onClick = { navController.popBackStack() },
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text("Назад")
                            }
                        }
                    }
                    else -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    )
}



