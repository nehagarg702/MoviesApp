package com.example.moviesapp.data.repository

import com.example.moviesapp.data.model.Movie
import com.example.moviesapp.network.NetworkResult
import com.example.moviesapp.network.TmdbApi
import com.example.moviesapp.utils.NetworkException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

class MovieRepositoryImpl(private val apiService : TmdbApi) : MovieRepository {

    private val _trendingMovies = MutableStateFlow<NetworkResult<List<Movie>>>(NetworkResult.Loading)
    override val trendingMovies: StateFlow<NetworkResult<List<Movie>>> = _trendingMovies

    private suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.let {
                    NetworkResult.Success(it)
                } ?: NetworkResult.Error("Empty response body")
            } else {
                NetworkResult.Error("Something went wrong.Please try again.")
            }
        }
        catch (e: NetworkException) {
            NetworkResult.Error(e.message!!)
        }
        catch (e: Exception) {
            NetworkResult.Error("Something went wrong.Please try again.")
        }
    }

    override suspend fun getTrendingMovies(apiKey: String) {
        _trendingMovies.value = NetworkResult.Loading
        _trendingMovies.value = safeApiCall {
            apiService.getTrendingMovies(apiKey)
        }.let { result ->
            when (result) {
                is NetworkResult.Success -> NetworkResult.Success((result.data).movies)
                is NetworkResult.Error -> result
                else -> NetworkResult.Error("Unknown error occurred")
            }
        }
    }
}