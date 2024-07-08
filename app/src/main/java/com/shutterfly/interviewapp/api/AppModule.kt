package com.shutterfly.interviewapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "8db86f877df1b0947df8bceba159ed89"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor(API_KEY))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val movieService: MovieService = retrofit.create(MovieService::class.java)
    val movieRepository: MovieRepository = MovieRepositoryImpl(movieService)
}

