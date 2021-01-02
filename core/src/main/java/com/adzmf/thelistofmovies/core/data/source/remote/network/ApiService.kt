package com.adzmf.thelistofmovies.core.data.source.remote.network

import com.adzmf.thelistofmovies.core.data.source.remote.response.ListMovieResponse
import com.adzmf.thelistofmovies.core.data.source.remote.response.MovieDetailResponse
import com.adzmf.thelistofmovies.core.utils.Utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): ListMovieResponse

    @GET("3/movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
    ): MovieDetailResponse

    @GET("3/search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("include_adult") includeAdult: Boolean = false,
    ): ListMovieResponse

}