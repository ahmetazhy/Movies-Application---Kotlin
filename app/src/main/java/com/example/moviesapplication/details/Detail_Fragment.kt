package com.example.moviesapplication.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.moviesapplication.R
import com.example.moviesapplication.databinding.DetailLayoutBinding

class Detailfragment : Fragment() {
    private lateinit var viewModell: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DetailLayoutBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val resultss = DetailfragmentArgs.fromBundle(requireArguments()).selectedMovies

        val viewModelFactory = DetailViewModelFactory(resultss)

        viewModell = ViewModelProvider(
            this, viewModelFactory
        )[DetailViewModel::class.java]

        binding.viewModel = this.viewModell


        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detailmenu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}
