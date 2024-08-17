package com.example.moviesapp.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Long,
    val title: String,
    @SerializedName("poster_path") val posterPath: String,
    val overview: String
)
