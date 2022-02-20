package com.example.moviesapplication.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.R
import com.example.moviesapplication.network.Genres

internal class GenereAdapter(private var moviesList: List<Genres>) :
    RecyclerView.Adapter<GenereAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var genre: TextView = view.findViewById(R.id.genre)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_generic, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.genre.text = movie.name
    }
    override fun getItemCount(): Int {
        return moviesList.size
    }
}

