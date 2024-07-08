package com.shutterfly.interviewapp.api

import com.shutterfly.interviewapp.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MovieRepository {
    suspend fun getMovieDetails(movieId: Int): Movie?
}


class MovieRepositoryImpl(private val service: MovieService) : MovieRepository {

    override suspend fun getMovieDetails(movieId: Int): Movie? {
        return withContext(Dispatchers.IO) {
            service.getMovieDetails(movieId)
        }
    }
}
