package com.example.moviesapplication.discovery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.network.PopularApi
import com.example.moviesapplication.network.Resultss
import kotlinx.coroutines.*

enum class MoviesApiStatus { LOADING, ERROR, DONE }

class Discovery_ViewModels : ViewModel() {

    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus>
        get() = _status

    private val _navigateToSelectedMovies = MutableLiveData<Resultss?>()

    val navigateToSelectedMovies: MutableLiveData<Resultss?>
        get() = _navigateToSelectedMovies

    fun displayMoviesDetails(resultss: Resultss) {
        _navigateToSelectedMovies.value = resultss
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedMovies.value = null
    }


    private val _properties = MutableLiveData<List<Resultss>>()
    val properties: LiveData<List<Resultss>>
        get() = _properties

    init {
        getPopularMovies()
    }

    fun getPopularMovies() {
        viewModelScope.launch {
               _status.value = MoviesApiStatus.LOADING
                var getPropertiesDeferred = PopularApi.retrofitService.getProperties()
                try {
                    var resultapi = getPropertiesDeferred.await()
                    _properties.postValue(resultapi.results)
                    Log.i("slm","$_properties.")
                    _status.value = MoviesApiStatus.DONE
                } catch (e: Exception) {
                    _status.value = MoviesApiStatus.ERROR

                    Log.i("www","$e")
                }
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING
                var getPropertiesDeferred = PopularApi.retrofitService.getTopRated()
                try {
                    var resultapi = getPropertiesDeferred.await()
                    _properties.postValue(resultapi.results)
                    _status.value = MoviesApiStatus.DONE
                } catch (e: Exception) {
                    _status.value = MoviesApiStatus.ERROR
                }
        }
    }

    fun getNowPlaying() {
        viewModelScope.launch {
            _status.value = MoviesApiStatus.LOADING
                var getPropertiesDeferred = PopularApi.retrofitService.getNowPlaying()
                try {
                    var resultapi = getPropertiesDeferred.await()

                    _properties.postValue(resultapi.results)
                    _status.value = MoviesApiStatus.DONE
                } catch (e: Exception) {
                    _status.value = MoviesApiStatus.ERROR

                }
        }
    }

}