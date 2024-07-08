package com.shutterfly.interviewapp.repository

import com.shutterfly.interviewapp.api.MovieService
import com.shutterfly.interviewapp.data.ResultWrapper
import com.shutterfly.interviewapp.util.DispatcherProvider
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService, private val dispatcherProvider: DispatcherProvider
) : MovieRepository {
    override suspend fun getMovieDetails(movieId: Int) = flow {
        emit(ResultWrapper.Loading())
        val response = service.getMovieDetails(movieId)
        emit(ResultWrapper.Success(response))
    }.catch {
        emit(ResultWrapper.Failure(it))
    }.flowOn(dispatcherProvider.io)
}


/**
 * review comments:
 * wrap data
 * di
 * inject dispatchers
 * add UT
 * use by viewModels
 * add use case
 */