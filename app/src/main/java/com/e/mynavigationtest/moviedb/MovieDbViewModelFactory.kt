package com.e.mymovieskotlin.moviedb

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieDbViewModelFactory(private val application: Application) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieDbViewModel::class.java)){
            return MovieDbViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}