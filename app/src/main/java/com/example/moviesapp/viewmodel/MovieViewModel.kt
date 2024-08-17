package com.example.moviesapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.BuildConfig
import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.network.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val movies = repository.trendingMovies

    private val _filteredMovies = MutableStateFlow<List<Movie>>(emptyList())
    val filteredMovies: StateFlow<List<Movie>> = _filteredMovies

    val searchQuery = mutableStateOf("")

    init {
        fetchTrendingMovies()
    }

    fun fetchTrendingMovies() {
        viewModelScope.launch {
            repository.getTrendingMovies(BuildConfig.TMDB_API_KEY)
            filterMovies(searchQuery.value)
        }
    }

    fun filterMovies(query: String) {
        searchQuery.value = query
        val moviesList = (movies.value as? NetworkResult.Success)?.data.orEmpty()
        _filteredMovies.value = moviesList.filter {
            it.title.contains(query, ignoreCase = true) || it.overview.contains(query, ignoreCase = true)
        }
    }

    fun getMovieDetail(movieId : Long) : Movie {
        val list =  _filteredMovies.value.filter { it.id == movieId }
        return list[0]
    }
}
