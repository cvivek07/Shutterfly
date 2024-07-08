package com.shutterfly.interviewapp.data.repository

import com.shutterfly.interviewapp.data.model.Movie
import com.shutterfly.interviewapp.common.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieDetails(movieId: Int): Flow<ResultWrapper<Movie>>
}