package com.shutterfly.interviewapp.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shutterfly.interviewapp.data.Movie
import com.shutterfly.interviewapp.R

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
		viewModel.movie.observe(this) { movie ->
			updateUI(movie)
		}
	}

	private fun refreshMovieDetails() {
		viewModel.refreshMovieDetails(movieId)
	}

	private fun updateUI(movie: Movie) {
		val titleTextView = findViewById<TextView>(R.id.movie_title)
		titleTextView.text = movie.title
	}
}