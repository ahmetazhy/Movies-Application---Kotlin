package com.example.moviesapplication.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapplication.network.Resultss

class DetailViewModel(resultss: Resultss) : ViewModel() {

    private val _selectedMovies = MutableLiveData<Resultss>()

    val selectedMovies: LiveData<Resultss>
        get() = _selectedMovies


    init {
        _selectedMovies.postValue(resultss)
    }

}
