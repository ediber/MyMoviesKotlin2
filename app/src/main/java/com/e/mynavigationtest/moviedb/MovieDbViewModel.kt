package com.e.mymovieskotlin.moviedb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.e.mymovieskotlin.database.getDatabase
import com.e.mymovieskotlin.domain.Movie
import com.e.mymovieskotlin.repository.MoviesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieDbViewModel(application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)

    private val moviesRepository = MoviesRepository(database)

    internal enum class Sort {
        TOP_RATED, UPCOMING, ALL
    }

    private val _sortLive = MutableLiveData<Sort>()

    private lateinit var _movies: LiveData<List<Movie>>

    val movies: LiveData<List<Movie>>
        get() {
            return _movies
        }

    init {

        _movies =
            Transformations.switchMap(_sortLive) {
                when (it){
                    Sort.TOP_RATED -> moviesRepository.topRatedMovies
                    Sort.UPCOMING -> moviesRepository.upcomingMovies
                    Sort.ALL -> moviesRepository.movies
                }
            }

    }


    fun refreshMovieDbData(type: String) {
        coroutineScope.launch {
            moviesRepository.refreshMovieDbData(type)
        }
    }

    fun movieClicked(movie: Movie) {
        coroutineScope.launch {
            moviesRepository.updateMovie(movie)
        }
    }

    fun switchToTopRated() {
        _sortLive.postValue(Sort.TOP_RATED)
    }

    fun switchToUpcoming() {
        _sortLive.postValue(Sort.UPCOMING)
    }

    fun switchToAll() {
        _sortLive.postValue(Sort.ALL)
    }

}