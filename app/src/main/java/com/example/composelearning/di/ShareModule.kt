package com.example.composelearning.di

import com.example.composelearning.remote.MovieRepository
import com.example.composelearning.remote.MovieRepositoryImpl
import com.example.composelearning.remote.MovieService
import com.example.composelearning.remote.RemoteDataSource
import com.example.composelearning.usecase.GetMovieUseCase
import com.example.composelearning.usecase.GetMoviesUseCase
import org.koin.dsl.module


private val dataModule = module {
    factory { RemoteDataSource(get()) }
    factory { MovieService() }
}

private val domainModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    factory { GetMoviesUseCase() }
    factory { GetMovieUseCase(get()) }
}

private val shareModule = listOf(dataModule, domainModule)

fun getShareModule() = shareModule