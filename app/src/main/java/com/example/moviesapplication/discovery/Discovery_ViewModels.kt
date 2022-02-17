package com.example.moviesapplication.discovery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    private var viewModelJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        getPopularMovies()
    }

    fun getPopularMovies() {
        ioScope.launch {
            withContext(Dispatchers.IO) {
                var getPropertiesDeferred = PopularApi.retrofitService.getProperties()
             /*   _status.value = MoviesApiStatus.LOADING*/
                try {
                    var resultapi = getPropertiesDeferred.await()

                    _properties.postValue(resultapi.results)
            /*        _status.value = MoviesApiStatus.DONE*/
                } catch (e: Exception) {
/*                    _status.value = MoviesApiStatus.ERROR*/
                }
            }
        }
    }

    fun getTopRatedMovies() {
        ioScope.launch{
            withContext(Dispatchers.IO) {
                var getPropertiesDeferred = PopularApi.retrofitService.getTopRated()
//                _status.value = MoviesApiStatus.LOADING
                try {
                    var resultapi = getPropertiesDeferred.await()

                    _properties.postValue(resultapi.results)
//                    _status.value = MoviesApiStatus.DONE
                } catch (e: Exception) {
//                    _status.value = MoviesApiStatus.ERROR
                }
            }
        }
    }

    fun getNowPlaying() {
        ioScope.launch {
            withContext(Dispatchers.IO) {
                var getPropertiesDeferred = PopularApi.retrofitService.getNowPlaying()
/*                _status.value = MoviesApiStatus.LOADING*/
                try {
                    var resultapi = getPropertiesDeferred.await()

                    _properties.postValue(resultapi.results)
/*                    _status.value = MoviesApiStatus.DONE*/
                } catch (e: Exception) {
        /*            _status.value = MoviesApiStatus.ERROR*/

                }
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