package com.zg.netflixcmp.di

import com.zg.netflixcmp.core.persistence.DatabaseFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { DatabaseFactory(androidApplication()) }
    }