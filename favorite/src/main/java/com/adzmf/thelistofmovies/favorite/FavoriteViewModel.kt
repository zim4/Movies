package com.adzmf.thelistofmovies.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adzmf.thelistofmovies.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()
}

