package com.shutterfly.interviewapp.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shutterfly.interviewapp.data.model.Movie
import com.shutterfly.interviewapp.R
import com.shutterfly.interviewapp.common.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    private val movieId = 1891

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this)[MovieViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        observeMovieData()
        refreshMovieDetails()
    }

    private fun observeMovieData() {
        viewModel.movie.observe(this) { result ->
            when (result) {
                is ResultWrapper.Loading -> showLoading()
                is ResultWrapper.Success -> updateUI(result.data)
                is ResultWrapper.Failure -> showError(result.throwable)
            }
        }
    }

    private fun refreshMovieDetails() {
        viewModel.refreshMovieDetails(movieId)
    }
    private fun showLoading() {
        val titleTextView = findViewById<TextView>(R.id.movie_title)
        titleTextView.text = "Loading..."
    }
    private fun updateUI(movie: Movie) {
        // can be extracted as a global var or use binding
        val titleTextView = findViewById<TextView>(R.id.movie_title)
        titleTextView.text = movie.title
    }
    private fun showError(exception: Throwable) {
        val titleTextView = findViewById<TextView>(R.id.movie_title)
        titleTextView.text = exception.message
    }
}