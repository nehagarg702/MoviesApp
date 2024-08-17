package com.example.moviesapp.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.network.NetworkResult
import com.example.moviesapp.utils.UiState
import com.example.moviesapp.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieListScreen(
    viewModel: MovieViewModel,
    onMovieClick: (Long) -> Unit,
) {
    val moviesState by viewModel.movies.collectAsState()
    val filteredMovies by viewModel.filteredMovies.collectAsState()

    val refreshing = remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = refreshing.value,
        onRefresh = {
            refreshing.value = true
            viewModel.fetchTrendingMovies()
            refreshing.value = false
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(searchQuery = viewModel.searchQuery, viewModel)

            BaseScreen(
                uiState = moviesState,
                onRetry = { viewModel.fetchTrendingMovies() }
            ) {

                if (filteredMovies.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = "No results found",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        items(filteredMovies) { movie ->
                            MovieItem(movie = movie, onMovieClick)
                        }
                    }
                }
            }
        }
        PullRefreshIndicator(
            refreshing = refreshing.value,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
            backgroundColor = MaterialTheme.colorScheme.primary,
            contentColor = Color(0xFFEC4172)
        )
    }
}
