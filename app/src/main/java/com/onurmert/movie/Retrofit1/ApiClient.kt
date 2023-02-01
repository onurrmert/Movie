package com.onurmert.retro4fitt.Retrofit1

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient (){

    private val BASE_URL = "http://www.omdbapi.com/"

    private fun getRetrofit() : Retrofit{

        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }

    companion object{
        fun getMovieApi() : IMovieApi{
            return ApiClient().getRetrofit().create(IMovieApi::class.java)
        }
    }
}
