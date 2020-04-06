package com.e.mymovieskotlin.details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.e.mymovieskotlin.database.getDatabase
import com.e.mymovieskotlin.domain.Movie
import com.e.mymovieskotlin.repository.MoviesRepository

class DetailsViewModel(application: Application) : AndroidViewModel(application) {


    private val database = getDatabase(application)

    private val repository = MoviesRepository(database)

    lateinit var posterPath: String

    private  var _movieValue: Movie? =
    repository.currentMovieInList.value?.get(0)

 //   private val _title = MutableLiveData<String>(_movieValue?.title)


    private val _title =
        MutableLiveData<String>(_movieValue?.title)

    val title: LiveData<String>
        get() = _title

 /*   private val _overview = MutableLiveData<String>(_movieValue?.overview)
    val overview: LiveData<String>
        get() = _overview

    private val _posterPath =
        MutableLiveData<String>(_movieValue?.posterBasePath + _movieValue?.posterPath)
    val posterPath: LiveData<String>
        get() = _posterPath*/

    fun getMovieById(posterPath: String) {
       /* val currentMovieInList = repository.getMovieById(posterPath)
        val a = currentMovieInList.value?.get(0)
        _movieValue = a as Movie*/

        repository.posterPath = posterPath
    }

}
