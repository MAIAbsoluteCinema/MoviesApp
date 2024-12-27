package com.example.movies.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movies.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val userViewModel: UserViewModel = viewModel()
                    AppNavigation(userViewModel)
                }
            }
        }
    }
}

@Composable
fun AppNavigation(userViewModel: UserViewModel) {
    val navController = rememberNavController()

    val startDestination = if (userViewModel.isUserLoggedIn()) {
        "main"
    } else {
        "login"
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("main") { MainScreen(navController) }
        composable("movie_detail/{movieTitle}") { backStackEntry ->
            val movieTitle = backStackEntry.arguments?.getString("movieTitle")
            MovieDetailScreen(navController = navController, movieTitle = movieTitle)
        }
    }
}