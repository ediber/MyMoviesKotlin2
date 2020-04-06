package com.e.mymovieskotlin.moviedb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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

    private val _movies = moviesRepository.movies
    val movies: LiveData<List<Movie>>
        get() {
            return _movies
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


}