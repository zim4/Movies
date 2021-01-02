package com.adzmf.thelistofmovies.core.data.source.local.room

import androidx.room.*
import com.adzmf.thelistofmovies.core.data.source.local.entity.FavoriteMovieEntity
import com.adzmf.thelistofmovies.core.data.source.local.entity.MovieDetailEntity
import com.adzmf.thelistofmovies.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie_detail WHERE id = :id")
    fun getMovieDetails(id: Int): Flow<List<MovieDetailEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieDetailList: List<MovieDetailEntity>)

    @Query("SELECT * FROM favorite_movie")
    fun getFavoriteMovies(): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT * FROM favorite_movie WHERE id = :id")
    fun getFavoriteMoviesById(id: Int): Flow<List<FavoriteMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(favoriteMovie: FavoriteMovieEntity)

    @Delete
    fun deleteFavoriteMovie(favoriteMovie: FavoriteMovieEntity)

}