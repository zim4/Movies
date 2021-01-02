package com.adzmf.thelistofmovies.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adzmf.thelistofmovies.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getAllMovies().asLiveData()
}

