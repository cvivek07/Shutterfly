package com.shutterfly.interviewapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shutterfly.interviewapp.data.Movie
import com.shutterfly.interviewapp.data.ResultWrapper
import com.shutterfly.interviewapp.domain.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) :
    ViewModel() {

    private val _movie = MutableLiveData<ResultWrapper<Movie>>()
    val movie: LiveData<ResultWrapper<Movie>> get() = _movie

    fun refreshMovieDetails(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailsUseCase(movieId).collect { result ->
                _movie.value = result
            }
        }
    }
}
