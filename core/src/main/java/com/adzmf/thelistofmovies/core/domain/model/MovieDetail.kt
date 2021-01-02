package com.adzmf.thelistofmovies.core.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val originalLanguage: String,
    val releaseDate: String,
    val runtime: Int,
    val overview: String,
    val voteAverage: Double,
    val genres: String,
)