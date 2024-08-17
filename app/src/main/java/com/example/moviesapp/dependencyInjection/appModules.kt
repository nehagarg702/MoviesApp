package com.example.moviesapp.dependencyInjection

import android.app.Application
import com.example.moviesapp.data.repository.MovieRepository
import com.example.moviesapp.data.repository.MovieRepositoryImpl
import com.example.moviesapp.network.NetworkConnectionInterceptor
import com.example.moviesapp.network.TmdbApi
import com.example.moviesapp.utils.NetworkConnectivity
import com.example.moviesapp.viewmodel.MovieViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

var appModules = module {
    single(named("application")) { androidApplication() }
    single(named("applicationContext")) { get<Application>(named("application")).applicationContext }
    single { NetworkConnectivity(get(named("applicationContext"))) }
    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(NetworkConnectionInterceptor(get())) // Add the network interceptor
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbApi::class.java)
    }

    single<MovieRepository> { MovieRepositoryImpl(get()) }
    viewModel { MovieViewModel(get()) }
}
