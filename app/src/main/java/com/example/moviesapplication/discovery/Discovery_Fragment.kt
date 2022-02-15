package com.example.moviesapplication.discovery

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.moviesapplication.R
import com.example.moviesapplication.databinding.DiscoveryLayoutBinding


class Discovery_Fragment : Fragment() {

    private lateinit var binding: DiscoveryLayoutBinding
    private lateinit var viewModel: Discovery_ViewModels
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DiscoveryLayoutBinding.inflate(
            inflater, container, false
        )
        viewModel = ViewModelProvider(this).get(Discovery_ViewModels::class.java)

        binding.viewModel = viewModel

        val adapter = ResultAdapter(ResultAdapter.OnClickListener {
            viewModel.displayMoviesDetails(it)

        })
        binding.weekList.adapter = adapter

//        binding.weekList.adapter = ResultAdapter(ResultAdapter.OnClickListener {
//            viewModel.displayMoviesDetails(it)
//        })


        viewModel.navigateToSelectedMovies.observe(viewLifecycleOwner) {
            if (null != it) {
                findNavController().navigate(
                    Discovery_FragmentDirections.actionDiscoveryFragmentToDetailFragment(it)
                )
                viewModel.displayPropertyDetailsComplete()
            }
        }


        viewModel.properties.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()

                Log.i("submit", "$it")
            } else {
                adapter.submitList(listOf())
            }
        }


        binding.lifecycleOwner = this
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.sort, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.popularitem) {
            Toast.makeText(this.context, "popular", Toast.LENGTH_LONG).show()
            viewModel.getPopularMovies()
        } else if (item.itemId == R.id.toprated) {
            viewModel.getTopRatedMovies()
        } else if (item.itemId == R.id.nowplaying) {
            viewModel.getNowPlaying()
        }
        return true
    }
}