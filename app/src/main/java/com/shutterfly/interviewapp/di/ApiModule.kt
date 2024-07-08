package com.shutterfly.interviewapp.di

import com.shutterfly.interviewapp.data.network.ApiKeyInterceptor
import com.shutterfly.interviewapp.data.repository.MovieRepository
import com.shutterfly.interviewapp.data.repository.MovieRepositoryImpl
import com.shutterfly.interviewapp.data.network.MovieService
import com.shutterfly.interviewapp.domain.GetMovieDetailsUseCase
import com.shutterfly.interviewapp.util.DefaultDispatcherProvider
import com.shutterfly.interviewapp.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "8db86f877df1b0947df8bceba159ed89"

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): ApiKeyInterceptor {
        return ApiKeyInterceptor(API_KEY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(movieService: MovieService, dispatcherProvider: DispatcherProvider): MovieRepository {
        return MovieRepositoryImpl(movieService, dispatcherProvider)
    }

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(
        repository: MovieRepository
    ): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repository)
    }
}

