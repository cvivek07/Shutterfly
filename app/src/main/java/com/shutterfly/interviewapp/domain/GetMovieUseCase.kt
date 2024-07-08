package com.shutterfly.interviewapp.domain

import com.shutterfly.interviewapp.data.Movie
import com.shutterfly.interviewapp.data.ResultWrapper
import com.shutterfly.interviewapp.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): Flow<ResultWrapper<Movie>> {
        return repository.getMovieDetails(movieId)
    }
}
