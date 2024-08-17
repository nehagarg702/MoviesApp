package com.example.moviesapp.data.repository

import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.network.NetworkResult
import kotlinx.coroutines.flow.StateFlow

interface MovieRepository {

    val trendingMovies: StateFlow<NetworkResult<List<Movie>>>
    suspend fun getTrendingMovies(apiKey : String)
}