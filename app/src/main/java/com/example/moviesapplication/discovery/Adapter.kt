package com.example.moviesapplication.discovery

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.adapters.ViewBindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapplication.databinding.DiscoveryItemBinding
import com.example.moviesapplication.network.PopularProperty
import com.example.moviesapplication.network.Resultss
import timber.log.Timber

class ResultAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Resultss, ResultAdapter.ResultssViewHolder>(DiffCallback) {

    class ResultssViewHolder(private var binding: DiscoveryItemBinding ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultss: Resultss) {

            binding.property = resultss
            binding.executePendingBindings()

            Log.i("mmm","${resultss.original_title.toString()}")

        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Resultss>() {
        override fun areItemsTheSame(oldItem: Resultss, newItem: Resultss): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Resultss,newItem: Resultss): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultssViewHolder {
        return ResultssViewHolder(DiscoveryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ResultssViewHolder, position: Int) {
        val resultss = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(resultss)
        }
        holder.bind(resultss)
    }

    class OnClickListener(val clickListener: (result: Resultss) -> Unit) {
        fun onClick(resultss: Resultss) = clickListener(resultss)
    }

}