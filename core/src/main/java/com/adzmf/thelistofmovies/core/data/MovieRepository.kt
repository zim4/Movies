package com.adzmf.thelistofmovies.core.data

import com.adzmf.thelistofmovies.core.data.source.local.LocalDataSource
import com.adzmf.thelistofmovies.core.data.source.remote.RemoteDataSource
import com.adzmf.thelistofmovies.core.data.source.remote.network.ApiResponse
import com.adzmf.thelistofmovies.core.data.source.remote.response.MovieDetailResponse
import com.adzmf.thelistofmovies.core.data.source.remote.response.MovieResponse
import com.adzmf.thelistofmovies.core.domain.model.Movie
import com.adzmf.thelistofmovies.core.domain.model.MovieDetail
import com.adzmf.thelistofmovies.core.domain.repository.IMovieRepository
import com.adzmf.thelistofmovies.core.utils.AppExecutors
import com.adzmf.thelistofmovies.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getAllMovies().map { DataMapper.mapMovieEntitiesToDomain(it) }

            override fun shouldFetch(data: List<Movie>?): Boolean = true //data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()


    override fun getMovieDetail(id: Int): Flow<Resource<List<MovieDetail>>> =
        object : NetworkBoundResource<List<MovieDetail>, List<MovieDetailResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<MovieDetail>> =
                localDataSource.getMovieDetails(id).map { DataMapper.mapMovieDetailEntitiesToDomain(it) }

            override fun shouldFetch(data: List<MovieDetail>?): Boolean = true //data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieDetailResponse>>> =
                remoteDataSource.getMovieDetail(id)

            override suspend fun saveCallResult(data: List<MovieDetailResponse>) {
                val movieList = DataMapper.mapMovieDetailResponsesToEntities(data)
                localDataSource.insertMovieDetail(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map { DataMapper.mapFavoriteMovieEntitiesToMovieDomain(it) }
    }

    override fun getFavoriteMoviesById(id: Int): Flow<List<Movie>> {
        return localDataSource.getFavoriteMoviesById(id).map { DataMapper.mapFavoriteMovieEntitiesToMovieDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val favoriteMovieEntity = DataMapper.mapMovieDomainToFavoriteMovieEntity(movie)
        appExecutors.diskIO().execute {
            if (state) localDataSource.insertFavoriteMovie(favoriteMovieEntity)
            else localDataSource.deleteFavoriteMovie(favoriteMovieEntity)
        }
    }


}