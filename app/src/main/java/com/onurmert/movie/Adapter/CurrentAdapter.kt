package com.onurmert.movie.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.onurmert.movie.Model.MovieModel
import com.onurmert.movie.R
import com.onurmert.movie.View.CurrentFragmentDirections
import com.onurmert.movie.databinding.CurrentRecyclerRowBinding

class CurrentAdapter(var movieList : ArrayList<MovieModel>) : RecyclerView.Adapter<CurrentAdapter.CurrentViewholder>() {

    class CurrentViewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = CurrentRecyclerRowBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentViewholder {

        val itemview = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.current_recycler_row, parent, false)

        return CurrentViewholder(itemview)
    }

    override fun onBindViewHolder(holder: CurrentViewholder, position: Int) {

        Glide.with(holder.binding.currentRowImageView.context)
            .load(movieList.get(position).poster)
            .error(R.drawable.ic_launcher_background)
            .into(holder.binding.currentRowImageView)

        holder.binding.ratingText.setText(movieList.get(position).imdbRating)

        holder.binding.yearText.setText(movieList.get(position).year)

        holder.binding.curentRecyclerView.setOnClickListener {
            navigate1(it, movieList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFilterList(movieFilterList: ArrayList<MovieModel>){
        movieList.clear()
        movieList.addAll(movieFilterList)
        notifyDataSetChanged()
    }

    private fun navigate1(view : View, movieModel: MovieModel){

        val direction = CurrentFragmentDirections
            .actionCurrentFragmentToMovieDetailFragment(
                movieModel.title,
                movieModel.plot,
                movieModel.imdbRating,
                movieModel.poster)

        Navigation.findNavController(view).navigate(direction)
    }
}