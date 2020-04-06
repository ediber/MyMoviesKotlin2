package com.e.mymovieskotlin.domain

import com.squareup.moshi.Json

data class Movie(
    //  val popularity: Double,
    val movieId: Long,
    @Json(name = "poster_path")
    val posterPath: String,
    val title: String,
    var isSelected: Boolean,
    val overview: String
) {
    val posterBasePath = "https://image.tmdb.org/t/p/original///"
}