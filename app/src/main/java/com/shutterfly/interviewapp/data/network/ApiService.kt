package com.shutterfly.interviewapp.data.network

import com.shutterfly.interviewapp.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): Movie
}