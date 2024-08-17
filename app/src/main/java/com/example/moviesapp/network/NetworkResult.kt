package com.example.moviesapp.network

sealed class NetworkResult<out T> {
    data object Loading : NetworkResult<Nothing>()
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String) : NetworkResult<Nothing>()
}
