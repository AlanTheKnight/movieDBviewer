package com.example.moviedbviewer.service

import com.example.moviedbviewer.models.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TimetableService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey : String,
                         @Query("page") page : Int) : Call<Response>

    @GET("search/movie")
    fun searchMovies(
        @Query("api_key") apiKey : String,
        @Query("page") page : Int,
        @Query("query") text: String) : Call<Response>
}