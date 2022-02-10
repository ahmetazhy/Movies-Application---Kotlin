package com.example.moviesapplication.network

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularProperty (
    val page : Int,
    val results : List<Resultss>,
    val total_pages : Int,
    val total_results : Int
)

@JsonClass(generateAdapter = true)

data class Resultss (

    val adult : Boolean,
    val backdrop_path : String,
    val genre_ids : List<Int>,
    val id : Int?=null,
    val original_language : String,
    val original_title : String?=null,
    val overview : String,
    val popularity : Double,
    @Json(name = "poster_path") val poster_path: String,
    val release_date : String,
    val title : String,
    val video : Boolean,
    val vote_average : Double,
    val vote_count : Int
)

