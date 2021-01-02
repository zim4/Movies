package com.adzmf.thelistofmovies.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adzmf.thelistofmovies.core.domain.model.Movie
import com.adzmf.thelistofmovies.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)

    fun movieDetails(id: Int) = movieUseCase.getMovieDetails(id).asLiveData()
    fun favoriteMoviesById(id: Int) = movieUseCase.getFavoriteMoviesById(id).asLiveData()
}

