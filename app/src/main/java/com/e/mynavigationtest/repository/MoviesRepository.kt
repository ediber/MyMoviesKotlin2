package com.e.mymovieskotlin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.e.mymovieskotlin.database.MoviesDatabase
import com.e.mymovieskotlin.database.asDomainModel
import com.e.mymovieskotlin.domain.Movie
import com.e.mymovieskotlin.network.MoviesApiService
import com.e.mymovieskotlin.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(private val database: MoviesDatabase) {



    val movies: LiveData<List<Movie>> =
        Transformations.map(database.movieDao.getMovies()) {
            it.asDomainModel()
        }

    val selectedMovies: LiveData<List<Movie>> =
        Transformations.map(database.movieDao.getSelectedMovies()) {
            it.asDomainModel()
        }


    suspend fun refreshMovieDbData() {
        withContext(Dispatchers.IO) {
            var getMoviesDeferred =
                MoviesApiService.MovieApi.retrofitService.getTopRated()
            var moviesList = getMoviesDeferred.await()

            database.movieDao.insertAll(moviesList.asDatabaseModel())


            /*   for ( movie in moviesList.asDatabaseModel()){
                   database.movieDao.insertMovie(movie)
               }*/
        }
    }

    suspend fun updateMovie(movie: Movie) {
        withContext(Dispatchers.IO) {
            database.movieDao.updateIsSelected(movie.movieId, movie.isSelected)
        }
    }

    //var currentMovieInList: LiveData<List<Movie>>? = null

    lateinit var posterPath: String

    val currentMovieInList: LiveData<List<Movie>> by lazy {
        Transformations.map(database.movieDao.getMovieById(posterPath)) {
            it.asDomainModel()
        }
    }


    fun getMovieById(posterPath: String): LiveData<List<Movie>> {
        val currentMovieInList =
            //    Transformations.map(database.movieDao.getMovieById(posterPath)) {
            Transformations.map(database.movieDao.getMovieById()) {
                it.asDomainModel()
            }

        return currentMovieInList
    }


}