package com.e.mymovieskotlin.selected

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.e.mymovieskotlin.database.getDatabase
import com.e.mymovieskotlin.repository.MoviesRepository

class SelectedViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)

    private val moviesRepository = MoviesRepository(database)

    private val _selectedMovies = moviesRepository.selectedMovies
    val selectedMovies
        get() = _selectedMovies
}