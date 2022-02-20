package com.example.moviesapplication.details


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.R
import com.example.moviesapplication.databinding.DetailLayoutBinding
import com.example.moviesapplication.network.Genres
import com.example.moviesapplication.network.Results


class Detailfragment : Fragment() {
    private lateinit var viewModell: DetailViewModel
    private var movieList = mutableListOf<Genres>()


    private var viewModelAdapter: DevByteAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModell.playlist.observe(viewLifecycleOwner, Observer<List<Results>> { videos ->
            videos?.apply {
                viewModelAdapter?.videos = videos
            }
        })
    }

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
        var layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        viewModell.selectedMovies.observe(viewLifecycleOwner) {
            movieList.addAll(it.genres)

            recyclerView.adapter = GenereAdapter(movieList)
        }


        // Trailer

        viewModelAdapter = DevByteAdapter(VideoClick {
            val packageManager = context?.packageManager ?: return@VideoClick
            var intent = Intent(Intent.ACTION_VIEW, it.launchUri)
            if(intent.resolveActivity(packageManager) == null) {
                    val youtubLink="https://www.youtube.com/watch?v="+it.key
                intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubLink))
            }

            startActivity(intent)
        })
        binding.recyclerViewTrailer.layoutManager=LinearLayoutManager(this.context,
            LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewTrailer.adapter = viewModelAdapter




        setHasOptionsMenu(true)
        return binding.root
    }


    private val Results.launchUri: Uri
        get() {
            val yotubeVideo="https://www.youtube.com/watch?v="+key
            val httpUri = Uri.parse(yotubeVideo)
            return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
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


}



