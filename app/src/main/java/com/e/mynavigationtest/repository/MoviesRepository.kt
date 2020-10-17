package com.e.mymovieskotlin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.e.mymovieskotlin.database.MoviesDatabase
import com.e.mymovieskotlin.database.asDomainModel
import com.e.mymovieskotlin.domain.Movie
import com.e.mymovieskotlin.network.MoviesApiService
import com.e.mymovieskotlin.network.MoviesList
import com.e.mymovieskotlin.network.asDatabaseModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val TOP_RATED = "top_rated"
const val UPCOMING = "upcoming"

class MoviesRepository(private val database: MoviesDatabase) {

    val movies: LiveData<List<Movie>> =
        Transformations.map(database.movieDao.getMovies()) {
            it.asDomainModel()
        }

    val selectedMovies: LiveData<List<Movie>> =
        Transformations.map(database.movieDao.getSelectedMovies()) {
            it.asDomainModel()
        }
    //
    var topRatedMovies: LiveData<List<Movie>> =
        Transformations.map(database.movieDao.getMovies(TOP_RATED)) {
            it.asDomainModel()
        }

    var upcomingMovies: LiveData<List<Movie>> =
        Transformations.map(database.movieDao.getMovies(UPCOMING)) {
            it.asDomainModel()
        }

    suspend fun refreshMovieDbData(type: String) {
        withContext(Dispatchers.IO) {
            database.movieDao.deleteAll(type)

            var getMoviesDeferred : Deferred<MoviesList>
            if (type == TOP_RATED){
                getMoviesDeferred = MoviesApiService.MovieApi.retrofitService.getTopRated()
            } else{
                getMoviesDeferred = MoviesApiService.MovieApi.retrofitService.getUpcoming()
            }

            var moviesList = getMoviesDeferred.await()

            database.movieDao.insertAll(moviesList.asDatabaseModel(type))
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