package com.example.moviesapp.network

import com.example.moviesapp.utils.NetworkConnectivity
import com.example.moviesapp.utils.NetworkException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val connectivity: NetworkConnectivity) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!connectivity.isNetworkAvailable()) {
            throw NetworkException()
        }
        return chain.proceed(chain.request())
    }
}