package com.adzmf.thelistofmovies.core.domain.repository

import com.adzmf.thelistofmovies.core.data.Resource
import com.adzmf.thelistofmovies.core.domain.model.Movie
import com.adzmf.thelistofmovies.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getMovieDetail(id: Int): Flow<Resource<List<MovieDetail>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun getFavoriteMoviesById(id: Int): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)

}