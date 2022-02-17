package com.example.moviesapplication.details


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.network.MoviesDetail
import com.example.moviesapplication.network.PopularApi
import com.example.moviesapplication.network.Resultss
import kotlinx.coroutines.*

class DetailViewModel(private val resultss: Int) : ViewModel() {

    private val _selectedMovies = MutableLiveData<MoviesDetail>()
    val selectedMovies: LiveData<MoviesDetail>
        get() = _selectedMovies

    private var viewModelJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getDetailMovies() {
        ioScope.launch {
            withContext(Dispatchers.IO) {
                var getPropertiesDeferred = PopularApi.retrofitService.getDetail(resultss)
                try {
                    var resultapi = getPropertiesDeferred.await()
                    _selectedMovies.postValue(resultapi)
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

    /**
     * This method will be called when this ViewModel is no longer used and will be destroyed.
     *
     *
     * It is useful when ViewModel observes some data and you need to clear this subscription to
     * prevent a leak of this ViewModel.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
