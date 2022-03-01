package com.example.moviesapplication.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapplication.R
import com.example.moviesapplication.network.Production_companies
import com.example.moviesapplication.network.Results

internal class CompaniesNameAdapter(private var companiesList: List<Production_companies>) :
    RecyclerView.Adapter<CompaniesNameAdapter.MyViewHolder>() {
      class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var compname: TextView = view.findViewById(R.id.compname)
        val imgcom:ImageView=view.findViewById(R.id.imgCom)

        fun bind(results: Production_companies) {
           compname.text=results.name

            val aa="https://image.tmdb.org/t/p/w500" + results.logo_path
            val imaageUrl = aa.toUri().buildUpon().scheme("https").build()
            Glide.with(imgcom.context)
                .load(imaageUrl)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imgcom )

        }
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_companies, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        val companies = companiesList[position]
        holder.bind(companies)

    }
    override fun getItemCount(): Int {
        return companiesList.size
    }
}