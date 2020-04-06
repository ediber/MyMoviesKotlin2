package com.e.mymovieskotlin.selected

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.e.mymovieskotlin.moviedb.MovieDbViewModel

class SelectedViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SelectedViewModel::class.java)){
            return SelectedViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}