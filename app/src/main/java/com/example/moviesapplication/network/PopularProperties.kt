package com.example.moviesapplication.network

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class PopularProperty (
    val page : Int,
    val results : List<Resultss>,
    val total_pages : Int,
    val total_results : Int
):Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Resultss (


    val id : Int?=null,
    val original_title : String?=null,
    @Json(name = "poster_path") val poster_path: String,
    val title : String,
):Parcelable


@JsonClass(generateAdapter = true)
@Parcelize
data class MoviesDetail(

    val adult : Boolean,
    val backdrop_path : String,
    val budget : Int,
    val genres : List<Genres>,
    val homepage : String,
    val id : Int,
    val imdb_id : String,
    val original_language : String,
    val original_title : String,
    val overview : String,
    val popularity : Double,
    val poster_path : String,
    val production_companies : List<Production_companies>,
    val production_countries : List<Production_countries>,
    val release_date : String,
    val revenue : Int,
    val runtime : Int,
    val spoken_languages : List<Spoken_languages>,
    val status : String,
    val tagline : String,
    val title : String,
    val video : Boolean,
    val vote_average : Double,
    val vote_count : Int,
    val videos : Videos,
    val images : Images
) : Parcelable


@JsonClass(generateAdapter = true)
@Parcelize
data class Genres(
    val id: Int,
    val name: String
) : Parcelable


@JsonClass(generateAdapter = true)
@Parcelize
data class Production_companies (

    val id : Int,
    val logo_path : String?=null,
    val name : String,
    val origin_country : String
):Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Production_countries (
    val iso_3166_1 : String,
    val name : String
):Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Videos (
    val results : List<Results>
):Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Images (
    val backdrops : List<String>,
    val logos : List<String>,
    val posters : List<String>
):Parcelable

@JsonClass(generateAdapter = true)
@Parcelize
data class Results (

    val iso_639_1 : String,
    val iso_3166_1 : String,
    val name : String,
    val key : String,
    val site : String,
    val size : Int,
    val type : String,
    val official : Boolean,
    val published_at : String,
    val id : String
):Parcelable


@JsonClass(generateAdapter = true)
@Parcelize
data class Spoken_languages (

    val english_name : String,
    val iso_639_1 : String,
    val name : String
):Parcelable