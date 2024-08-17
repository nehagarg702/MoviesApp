package com.example.moviesapp.ui.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviesapp.viewmodel.MovieViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {
    val viewModel: MovieViewModel = koinViewModel()
    NavHost(navController = navController, startDestination = "movie_list") {
        composable("movie_list") {
            MovieListScreen(
                onMovieClick = { movieId ->
                    navController.navigate("movieDetail/$movieId")
                },
                viewModel = viewModel
            )
        }
        composable("movieDetail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toLong() ?: 0
            val movie = viewModel.getMovieDetail(movieId)
            MovieDetailScreen(
                movie,
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}
