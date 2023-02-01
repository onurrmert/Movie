package com.onurmert.movie.View

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.onurmert.movie.Adapter.CurrentAdapter
import com.onurmert.movie.Model.MovieModel
import com.onurmert.movie.ViewModel.CurrentViewModel
import com.onurmert.movie.databinding.FragmentCurrentBinding
import java.util.*
import kotlin.collections.ArrayList

class CurrentFragment : Fragment() {

    private lateinit var binding: FragmentCurrentBinding

    private lateinit var viewModel: CurrentViewModel

    private val movieList = ArrayList<MovieModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCurrentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.toolbarText.setText("Movie")

        viewModel = ViewModelProvider(requireActivity()).get(CurrentViewModel::class.java)

        getMovie()
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }

    private fun getMovie(){

        viewModel.getMovie()

        viewModel.movieModel.observe(viewLifecycleOwner, Observer {
            search()

            movieList.clear()

            movieList.add(it)
        })
    }

    private fun search(){

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter1(newText!!)
                return true
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filter1(text : String){

        val filterList = ArrayList<MovieModel>()

        movieList.forEach {
            if (it.title.toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))){
                filterList.add(it)
            }
        }

        initRecycler(filterList)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecycler(filterList : ArrayList<MovieModel>){

        val currentAdapter = CurrentAdapter(movieList)

        if (filterList.isEmpty()){
            binding.progressBar.visibility = View.VISIBLE
            binding.loadingText.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
            binding.loadingText.setText("No movie found")
        }
        else{

            binding.recyclerView.visibility = View.VISIBLE
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerView.adapter = currentAdapter

            currentAdapter.setFilterList(filterList)
            currentAdapter.notifyDataSetChanged()

            binding.progressBar.visibility = View.GONE
            binding.loadingText.visibility = View.GONE
        }
    }

    private fun refresh(){
        binding.swipeRefresh.setOnRefreshListener {
            getMovie()
            binding.swipeRefresh.isRefreshing = false
        }
    }
}