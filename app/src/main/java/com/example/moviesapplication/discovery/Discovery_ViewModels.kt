package com.example.moviesapplication.discovery

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.moviesapplication.network.PopularApi
import com.example.moviesapplication.network.Resultss
import kotlinx.coroutines.*
import timber.log.Timber

enum class popularApiStatus { LOADING, ERROR, DONE }

class Discovery_ViewModels() : ViewModel() {

    private val _status = MutableLiveData<popularApiStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<popularApiStatus>
        get() = _status

    private val _navigateToSelectedMovies= MutableLiveData<Resultss?>()

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


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun getPopularMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                var getPropertiesDeferred = PopularApi.retrofitService.getProperties()

                try {
                    var resultapi = getPropertiesDeferred.await()
                    Log.i(
                        "problem","${resultapi.results}"
                    )
                    _properties.postValue(resultapi.results)
                } catch (e: Exception) {

                }
            }
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                var getPropertiesDeferred = PopularApi.retrofitService.getTopRated()

                try {
                    var resultapi = getPropertiesDeferred.await()
                    Log.i(
                        "problem","${resultapi.results}"
                    )
                    _properties.postValue(resultapi.results)
                } catch (e: Exception) {

                }
            }
        }
    }

    fun getNowPlaying() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                var getPropertiesDeferred = PopularApi.retrofitService.getNowPlaying()

                try {
                    var resultapi = getPropertiesDeferred.await()
                    Log.i(
                        "problem","${resultapi.results}"
                    )
                    _properties.postValue(resultapi.results)
                } catch (e: Exception) {

                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}