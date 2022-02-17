package com.example.moviesapplication.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.R
import com.example.moviesapplication.databinding.DetailLayoutBinding
import com.example.moviesapplication.network.Genres


class Detailfragment : Fragment() {
    private lateinit var viewModell: DetailViewModel
    private var movieList = mutableListOf<Genres>()
    private lateinit var moviesAdapter: GenereAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DetailLayoutBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val resultss = DetailfragmentArgs.fromBundle(requireArguments()).selectedMovieId

        val viewModelFactory = DetailViewModelFactory(resultss)

        viewModell = ViewModelProvider(
            this, viewModelFactory
        )[DetailViewModel::class.java]

        binding.viewModel = this.viewModell

        val recyclerView: RecyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        viewModell.selectedMovies.observe(viewLifecycleOwner) {
            movieList.addAll(it.genres)

            recyclerView.adapter = GenereAdapter(movieList)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detailmenu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        )
                || super.onOptionsItemSelected(item)
    }


//    class MovieModel(genre: String?) {
//        private var genre: String
//
//        init {
//            this.genre = genre!!
//
//        }
//
//
//        fun getGenre(): String? {
//            return genre
//        }
//
//    }

}