package com.example.moviesapplication.details



import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapplication.network.MoviesDetail
import com.example.moviesapplication.network.PopularApi
import com.example.moviesapplication.network.Results
import kotlinx.coroutines.*

class DetailViewModel(private val resultss: Int) : ViewModel() {

    private val _selectedMovies = MutableLiveData<MoviesDetail>()
    val selectedMovies: LiveData<MoviesDetail>
        get() = _selectedMovies

    private val _playlist = MutableLiveData<List<Results>>()
    val playlist: LiveData<List<Results>>
        get() = _playlist

    private var viewModelJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getDetailMovies() {
        ioScope.launch {
            withContext(Dispatchers.IO) {
                var getPropertiesDeferred = PopularApi.retrofitService.getDetail(resultss)
                try {
                    var resultapi = getPropertiesDeferred.await()
                    _selectedMovies.postValue(resultapi)
                    _playlist.postValue(resultapi.videos.results)
                } catch (e: Exception) {
                    Log.i("Exception!!!!","${e.message}")
                }
            }
        }
    }


    init {
        ioScope.launch {
            withContext(Dispatchers.IO){
                getDetailMovies()
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
