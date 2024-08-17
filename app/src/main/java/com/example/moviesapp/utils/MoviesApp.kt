package com.example.moviesapp.utils

import android.app.Application
import com.example.moviesapp.dependencyInjection.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //Start Koin
        startKoin {
            androidContext(this@MoviesApp)
            modules(appModules)
        }
    }
}