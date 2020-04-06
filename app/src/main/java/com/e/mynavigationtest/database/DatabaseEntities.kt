package com.e.mymovieskotlin.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.e.mymovieskotlin.domain.Movie
import com.squareup.moshi.Json
import kotlin.random.Random

@Entity(tableName = "database_movie")
data class DatabaseMovie(

    @ColumnInfo(name = "movie_id")
    var movieId: Long = Random.nextLong(Long.MAX_VALUE),

    val popularity: Double,

    @PrimaryKey
    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    val title: String,

    @ColumnInfo(name = "is_selected")
    val isSelected: Boolean = false,

    val overview: String
)


fun List<DatabaseMovie>.asDomainModel(): List<Movie> {
    return map { databaseMovie ->
        Movie(
            posterPath = databaseMovie.posterPath,
            title = databaseMovie.title,
            isSelected = databaseMovie.isSelected,
            movieId = databaseMovie.movieId,
            overview = databaseMovie.overview
        )
    }
}

/*fun DatabaseMovie.asDomainModel() = {
    Movie(
        posterPath = posterPath,
        title = title,
        isSelected = isSelected,
        movieId = movieId,
        overview = overview
    )
}*/

