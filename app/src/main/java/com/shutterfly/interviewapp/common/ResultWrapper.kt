package com.shutterfly.interviewapp.common

sealed class ResultWrapper<T> {
    data class Loading<T>(val data: T? = null) : ResultWrapper<T>()
    data class Success<T>(val data: T) : ResultWrapper<T>()
    data class Failure<T>(val throwable: Throwable) : ResultWrapper<T>()
}