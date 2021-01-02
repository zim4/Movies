package com.adzmf.thelistofmovies.core.domain.usecase

import com.adzmf.thelistofmovies.core.domain.model.Movie
import com.adzmf.thelistofmovies.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getAllMovies() = movieRepository.getAllMovies()
    override fun getMovieDetails(id: Int) = movieRepository.getMovieDetail(id)
    override fun getFavoriteMovies() = movieRepository.getFavoriteMovies()
    override fun getFavoriteMoviesById(id: Int) = movieRepository.getFavoriteMoviesById(id)
    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)

}