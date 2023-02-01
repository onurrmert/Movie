package com.onurmert.retro4fitt.Retrofit1

import com.onurmert.movie.Model.MovieModel
import retrofit2.Call
import retrofit2.http.GET

interface IMovieApi {

    @GET("?i=tt3896198&apikey=780d5173")
    fun getMovie() : Call<MovieModel>
}