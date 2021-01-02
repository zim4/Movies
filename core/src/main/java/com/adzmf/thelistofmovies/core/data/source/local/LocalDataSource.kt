package com.adzmf.thelistofmovies.core.data.source.local

import com.adzmf.thelistofmovies.core.data.source.local.entity.FavoriteMovieEntity
import com.adzmf.thelistofmovies.core.data.source.local.entity.MovieDetailEntity
import com.adzmf.thelistofmovies.core.data.source.local.entity.MovieEntity
import com.adzmf.thelistofmovies.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    suspend fun insertMovies(movieList: List<MovieEntity>) = movieDao.insertMovies(movieList)

    fun getMovieDetails(id: Int): Flow<List<MovieDetailEntity>> = movieDao.getMovieDetails(id)

    suspend fun insertMovieDetail(movieDetailList: List<MovieDetailEntity>) = movieDao.insertMovieDetail(movieDetailList)

    fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>> = movieDao.getFavoriteMovies()

    fun getFavoriteMoviesById(id: Int): Flow<List<FavoriteMovieEntity>> = movieDao.getFavoriteMoviesById(id)

    fun insertFavoriteMovie(favoriteMovie: FavoriteMovieEntity) = movieDao.insertFavoriteMovie(favoriteMovie)

    fun deleteFavoriteMovie(favoriteMovie: FavoriteMovieEntity) = movieDao.deleteFavoriteMovie(favoriteMovie)

}