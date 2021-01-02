package com.adzmf.thelistofmovies.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("original_title")
    val title: String?,

    @field:SerializedName("poster_path")
    val posterPath: String?,

    @field:SerializedName("backdrop_path")
    val backdropPath: String?,

    @field:SerializedName("original_language")
    val originalLanguage: String?,

    @field:SerializedName("release_date")
    val releaseDate: String?,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("overview")
    val overview: String?,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("genres")
    val genreResponses: List<GenreResponse>,
)