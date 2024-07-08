package com.shutterfly.interviewapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shutterfly.interviewapp.di.AppModule
import com.shutterfly.interviewapp.data.Movie
import com.shutterfly.interviewapp.api.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val repository : MovieRepository = AppModule.movieRepository

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    fun refreshMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val fetchedMovie = repository.getMovieDetails(movieId)
            _movie.postValue(fetchedMovie)
        }
    }
}
