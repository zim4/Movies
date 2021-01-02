package com.adzmf.thelistofmovies.core.domain.usecase

import com.adzmf.thelistofmovies.core.data.Resource
import com.adzmf.thelistofmovies.core.domain.model.Movie
import com.adzmf.thelistofmovies.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getMovieDetails(id: Int): Flow<Resource<List<MovieDetail>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun getFavoriteMoviesById(id: Int): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)

}