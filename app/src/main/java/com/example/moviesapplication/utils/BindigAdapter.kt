package com.example.moviesapplication.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapplication.R
import com.example.moviesapplication.discovery.MoviesApiStatus
import com.example.moviesapplication.discovery.ResultAdapter
import com.example.moviesapplication.network.Resultss
import javax.xml.transform.stream.StreamSource


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Resultss>?) {
    val adapter = recyclerView.adapter as ResultAdapter?
    adapter?.submitList(data)
    Log.i("ppp","$adapter")
}




@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    val aa="https://image.tmdb.org/t/p/w500"+imgUrl
    aa?.let {
        val imgUri = aa.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}


@BindingAdapter("generic")
fun TextView.setGeneric(date: Int?) {
    val generic = date
    this.text = generic.toString()
}


@BindingAdapter("moviesApiStatus")
fun bindStatus(statusImageView: ImageView, status: MoviesApiStatus?) {
    when (status) {
        MoviesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MoviesApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MoviesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}