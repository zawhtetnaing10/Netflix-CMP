package com.zg.netflixcmp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.zg.netflixcmp.core.persistence.AppDatabase
import com.zg.netflixcmp.core.persistence.DatabaseFactory
import com.zg.netflixcmp.movies.data.repositories.MoviesRepository
import com.zg.netflixcmp.movies.network.api_services.MoviesApiService
import com.zg.netflixcmp.movies.network.api_services.impls.MoviesApiServiceImpl
import com.zg.netflixcmp.movies.presentation.HomeViewModel
import com.zg.netflixcmp.movies.presentation.MovieDetailsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    // AppDatabase
    single<AppDatabase> {
        get<DatabaseFactory>()
            .create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    // Api Services
    single<MoviesApiService> { MoviesApiServiceImpl() }
    single<MoviesRepository> { MoviesRepository(movieApiService = get(), appDatabase = get()) }

    viewModelOf(::HomeViewModel)
    viewModel{ params ->
        MovieDetailsViewModel(
            movieId = params.get(),
            movieRepository = get()
        )
    }
}