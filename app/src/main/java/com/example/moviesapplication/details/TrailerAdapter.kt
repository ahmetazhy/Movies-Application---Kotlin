package com.example.moviesapplication.details

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapplication.R
import com.example.moviesapplication.databinding.TrailersItemBinding
import com.example.moviesapplication.network.Results

class DevByteAdapter(val callback: VideoClick) : RecyclerView.Adapter<DevByteViewHolder>() {
    var videos: List<Results> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevByteViewHolder {
        val withDataBinding: TrailersItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            DevByteViewHolder.LAYOUT,
            parent,
            false)
        return DevByteViewHolder(withDataBinding)
    }

    override fun getItemCount() = videos.size



    override fun onBindViewHolder(holder: DevByteViewHolder, position: Int) {

        holder.viewDataBinding.also {
            it.video = videos[position]
            it.videoCallback = callback

        }
        val results = videos[position]
        holder.bind(results)
    }

}

class VideoClick(val block: (Results) -> Unit) {
    fun onClick(video: Results) = block(video)
}


class DevByteViewHolder(val viewDataBinding: TrailersItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.trailers_item
    }
    fun bind(results: Results ) {
        viewDataBinding.title.text=results.name
     val thumbnail =viewDataBinding.videoTrailer

        val aa="http://img.youtube.com/vi/" + results.key+ "/0.jpg"
        val imaageUrl = aa.toUri().buildUpon().scheme("https").build()
        Glide.with(thumbnail.context)
            .load(imaageUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(thumbnail )
    }
}


