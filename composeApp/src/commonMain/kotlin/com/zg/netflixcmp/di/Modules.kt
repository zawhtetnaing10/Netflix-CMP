package com.zg.netflixcmp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.zg.netflixcmp.core.persistence.AppDatabase
import com.zg.netflixcmp.core.persistence.DatabaseFactory
import com.zg.netflixcmp.movies.data.repositories.MoviesRepository
import com.zg.netflixcmp.movies.network.api_services.MoviesApiService
import com.zg.netflixcmp.movies.network.api_services.impls.MoviesApiServiceImpl
import com.zg.netflixcmp.movies.presentation.HomeViewModel
import com.zg.netflixcmp.movies.presentation.MovieDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule: Module = module {
    single<AppDatabase> {
        get<DatabaseFactory>()
            .create()
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    single<MoviesApiService> { MoviesApiServiceImpl() }
    single<MoviesRepository> {
        MoviesRepository(
            movieApiService = get(),
            movieDao = get<AppDatabase>().movieDao()
        )
    }

    viewModel {
        HomeViewModel(movieRepository = get())
    }
    viewModel { params ->
        MovieDetailsViewModel(
            movieId = params.get(),
            movieRepository = get()
        )
    }
}