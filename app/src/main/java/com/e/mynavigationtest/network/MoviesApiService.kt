package com.e.mymovieskotlin.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// https://api.themoviedb.org/3/movie/top_rated?api_key=a921476d861fb36a167704c00cb03bfb&language=en-US&page=1
//         https://api.themoviedb.org/3/movie/upcoming?api_key=a921476d861fb36a167704c00cb03bfb&language=en-US&page=1

private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

class MoviesApiService {

    interface MoviesApiService{
        @GET("top_rated?api_key=a921476d861fb36a167704c00cb03bfb&language=en-US&page=1")
        fun getTopRated():
                Deferred<MoviesList>
    }

    object MovieApi{
        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .build()

        val retrofitService : MoviesApiService by lazy {
            retrofit.create(MoviesApiService::class.java)
        }
    }

}