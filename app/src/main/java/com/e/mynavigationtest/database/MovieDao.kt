package com.e.mymovieskotlin.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Query("select * from database_movie")
    fun getMovies(): LiveData<List<DatabaseMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: Array<DatabaseMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: DatabaseMovie)

    @Update
    fun update(databaseMovie: DatabaseMovie)

    @Query(
        "UPDATE database_movie SET is_selected = :isSelected WHERE movie_id == :id"
    )
    fun updateIsSelected(id: Long, isSelected: Boolean)

    @Query("select * from database_movie where is_selected == 1")
    fun getSelectedMovies(): LiveData<List<DatabaseMovie>>

    @Query("select * from database_movie where movie_id == :posterPath")
    fun getMovieById(posterPath: String) : LiveData<List<DatabaseMovie>>

    @Query("select * from database_movie ")
    fun getMovieById() : LiveData<List<DatabaseMovie>>

    @Query("DELETE FROM database_movie where list_type == :type")
    fun deleteAll(type: String)
}