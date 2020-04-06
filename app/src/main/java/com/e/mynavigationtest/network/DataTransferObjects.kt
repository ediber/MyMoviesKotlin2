package com.e.mymovieskotlin.network

import com.e.mymovieskotlin.database.DatabaseMovie
import com.squareup.moshi.Json

data class MoviesList(
    val results: List<NetworkMovie>
)

data class NetworkMovie(
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    val title: String,
    val overview: String
)

/*fun MoviesList.asDomainModel(): List<Movie> {
    return results.map { networkMovie ->
        Movie(
            posterPath = networkMovie.posterPath,
            title = networkMovie.title,
            isSelected = false
        )
    }
}*/

fun MoviesList.asDatabaseModel(type: String): Array<DatabaseMovie> {
    return results.map { networkMovie ->
        DatabaseMovie(
            posterPath = networkMovie.posterPath,
            title = networkMovie.title,
            isSelected = false,
            popularity = networkMovie.popularity,
            overview = networkMovie.overview,
            listType = type
        )
    }.toTypedArray()
}

/*
fun Movie.asDatabaseModel(): DatabaseMovie {
    return DatabaseMovie(
        posterPath = posterPath,
        title = title,
        isSelected = isSelected,
        movieId = movieId,
    )
}*/
