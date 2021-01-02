package com.adzmf.thelistofmovies.core.domain

import com.adzmf.thelistofmovies.core.domain.model.Movie
import com.adzmf.thelistofmovies.core.domain.repository.IMovieRepository
import com.adzmf.thelistofmovies.core.domain.usecase.MovieInteractor
import com.adzmf.thelistofmovies.core.domain.usecase.MovieUseCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieUseCaseTest {

    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var movieRepository: IMovieRepository

    @Before
    fun setUp() {
        movieUseCase = MovieInteractor(movieRepository)
    }

    @Test
    fun `should get data from repository`() {
        movieUseCase.getAllMovies()
        movieUseCase.getFavoriteMovies()
        movieUseCase.getMovieDetails(444)
        movieUseCase.getFavoriteMoviesById(444)

        verify(movieRepository).getAllMovies()
        verify(movieRepository).getFavoriteMovies()
        verify(movieRepository).getMovieDetail(444)
        verify(movieRepository).getFavoriteMoviesById(444)
        verifyNoMoreInteractions(movieRepository)
    }
}