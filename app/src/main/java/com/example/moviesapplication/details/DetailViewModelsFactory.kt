package com.example.moviesapplication.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapplication.network.Resultss

class DetailViewModelFactory(
    private val resultss: Int,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(resultss) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

