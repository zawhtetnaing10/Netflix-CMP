package com.zg.netflixcmp.movies.data.repositories

import com.zg.netflixcmp.di.platformModule
import com.zg.netflixcmp.di.sharedModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config : KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(sharedModule, platformModule)
    }
}