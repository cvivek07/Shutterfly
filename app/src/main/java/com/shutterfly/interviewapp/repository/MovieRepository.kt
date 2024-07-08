package com.shutterfly.interviewapp.repository

import com.shutterfly.interviewapp.data.Movie
import com.shutterfly.interviewapp.data.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieDetails(movieId: Int): Flow<ResultWrapper<Movie>>
}