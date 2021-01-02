package com.adzmf.thelistofmovies.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adzmf.thelistofmovies.core.data.source.local.entity.FavoriteMovieEntity
import com.adzmf.thelistofmovies.core.data.source.local.entity.MovieDetailEntity
import com.adzmf.thelistofmovies.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class, MovieDetailEntity::class, FavoriteMovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}