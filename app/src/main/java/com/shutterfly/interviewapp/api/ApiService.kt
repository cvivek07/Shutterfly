package com.shutterfly.interviewapp.api

import com.shutterfly.interviewapp.data.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): Movie
}