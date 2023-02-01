package com.onurmert.movie.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.onurmert.movie.R
import com.onurmert.movie.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

        binding.plotText.setText(getMovie().plot)

        binding.ratingText.setText(getMovie().imdbRating)

        binding.titleText.setText(getMovie().title)

        binding.toolbar.toolbarText.setText(getMovie().title)

        Glide.with(binding.movieImageView.context)
            .load(getMovie().poster)
            .error(R.drawable.ic_launcher_background)
            .into(binding.movieImageView)
    }

    private fun getMovie() : MovieDetailFragmentArgs{
        val bundle = arguments
        val args = MovieDetailFragmentArgs.fromBundle(bundle!!)
        return args
    }
}