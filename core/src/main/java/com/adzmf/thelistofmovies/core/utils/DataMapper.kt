package com.adzmf.thelistofmovies.core.utils

import com.adzmf.thelistofmovies.core.data.source.local.entity.FavoriteMovieEntity
import com.adzmf.thelistofmovies.core.data.source.local.entity.MovieDetailEntity
import com.adzmf.thelistofmovies.core.data.source.local.entity.MovieEntity
import com.adzmf.thelistofmovies.core.data.source.remote.response.MovieDetailResponse
import com.adzmf.thelistofmovies.core.data.source.remote.response.MovieResponse
import com.adzmf.thelistofmovies.core.domain.model.Movie
import com.adzmf.thelistofmovies.core.domain.model.MovieDetail

object DataMapper {

    fun mapMovieResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title ?: "-",
                posterPath = it.posterPath ?: "-",
                releaseDate = it.releaseDate ?: "-",
                overview = it.overview ?: "-",
                voteAverage = it.voteAverage,
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieResponsesToDomain(input: List<MovieResponse>): List<Movie> = mapMovieEntitiesToDomain(mapMovieResponsesToEntities(input))

    fun mapMovieDetailResponsesToEntities(input: List<MovieDetailResponse>): List<MovieDetailEntity> {
        val movieList = ArrayList<MovieDetailEntity>()
        input.map {
            val movie = MovieDetailEntity(
                id = it.id,
                title = it.title ?: "-",
                posterPath = it.posterPath ?: "-",
                backdropPath = it.backdropPath ?: "-",
                originalLanguage = it.originalLanguage ?: "-",
                releaseDate = it.releaseDate ?: "-",
                runtime = it.runtime,
                overview = it.overview ?: "-",
                voteAverage = it.voteAverage,
                genres = Utils.listToComma(it.genreResponses.map { g -> g.name }),
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieDetailEntitiesToDomain(input: List<MovieDetailEntity>): List<MovieDetail> {
        val movieList = ArrayList<MovieDetail>()
        input.map {
            val movie = MovieDetail(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                originalLanguage = it.originalLanguage,
                releaseDate = it.releaseDate,
                runtime = it.runtime,
                overview = it.overview,
                voteAverage = it.voteAverage,
                genres = it.genres
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        val movieList = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                overview = it.overview,
                voteAverage = it.voteAverage,
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapFavoriteMovieEntitiesToMovieDomain(input: List<FavoriteMovieEntity>): List<Movie> {
        val movieList = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                overview = it.overview,
                voteAverage = it.voteAverage,
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieDomainToFavoriteMovieEntity(input: Movie) = FavoriteMovieEntity(
        id = input.id,
        title = input.title,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        overview = input.overview,
        voteAverage = input.voteAverage,
    )

}