package com.onurmert.movie.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurmert.movie.Model.MovieModel
import com.onurmert.retro4fitt.Retrofit1.ApiClient
import retrofit2.Call
import retrofit2.Response

class CurrentViewModel : ViewModel() {

    val movieModel = MutableLiveData<MovieModel>()

    fun getMovie(){
        ApiClient.getMovieApi().getMovie().enqueue(object : retrofit2.Callback<MovieModel>{

            override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                if (response.isSuccessful){
                    if (response.body() != null){
                        movieModel.value = response.body()
                    }else{
                        Log.e("error: ", "body null")
                    }
                }else{
                    Log.e("error: ", "response is not successful")
                }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                Log.e("error api: ", t.localizedMessage!!)
            }
        })
    }
}