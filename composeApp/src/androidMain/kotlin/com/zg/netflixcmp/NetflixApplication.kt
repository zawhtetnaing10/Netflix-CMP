package com.zg.netflixcmp

import android.app.Application
import com.zg.netflixcmp.movies.data.repositories.initKoin
import org.koin.android.ext.koin.androidContext

class NetflixApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@NetflixApplication)
        }
    }
}