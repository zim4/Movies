package com.adzmf.thelistofmovies.core.data.source.remote

import android.util.Log
import com.adzmf.thelistofmovies.core.data.source.remote.network.ApiResponse
import com.adzmf.thelistofmovies.core.data.source.remote.network.ApiService
import com.adzmf.thelistofmovies.core.data.source.remote.response.MovieDetailResponse
import com.adzmf.thelistofmovies.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovies()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) emit(ApiResponse.Success(response.results))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieDetail(id: Int): Flow<ApiResponse<List<MovieDetailResponse>>> {
        return flow {
            try {
                val response = ArrayList<MovieDetailResponse>()
                response.add(apiService.getMovieDetail(id = id))
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}

