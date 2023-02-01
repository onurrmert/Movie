package com.onurmert.movie.Model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("imdbRating")
    val imdbRating: String = "",

    @SerializedName("Plot")
    val plot: String = "",

    @SerializedName("Title")
    val title: String = "",

    @SerializedName("Year")
    val year: String = "",

    @SerializedName("Poster")
    val poster: String = "",
)