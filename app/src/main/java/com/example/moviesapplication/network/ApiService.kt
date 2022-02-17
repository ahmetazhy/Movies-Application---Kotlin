package com.example.moviesapplication.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


private const val BASE_URL = "https://api.themoviedb.org/3/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

interface PopularApiService {

    @GET("movie/popular?api_key=f19914984d247f46e71e8a3e3d7bca02&language=en-US&page=1")
    fun getProperties(): Deferred<PopularProperty>

    @GET("movie/top_rated?api_key=f19914984d247f46e71e8a3e3d7bca02&language=en-US&page=1")
    fun getTopRated(): Deferred<PopularProperty>

    @GET("movie/now_playing?api_key=f19914984d247f46e71e8a3e3d7bca02&language=en-US&page=1")
    fun getNowPlaying(): Deferred<PopularProperty>

    @GET("movie/{movie_id}?language=en-US&page=1&api_key=f19914984d247f46e71e8a3e3d7bca02&append_to_response=videos,images")
    fun getDetail(
        @Path(value = "movie_id", encoded = false) key: Int,
    ): Deferred<MoviesDetail>

}


object PopularApi {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService =retrofit.create(PopularApiService::class.java)
}