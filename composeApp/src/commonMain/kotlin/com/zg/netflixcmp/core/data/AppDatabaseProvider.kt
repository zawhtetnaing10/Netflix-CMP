package com.zg.netflixcmp.core.data

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.zg.netflixcmp.core.persistence.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

object AppDatabaseProvider {
    var appDatabase: AppDatabase? = null

    fun initializeAppDatabase(databaseBuilder: RoomDatabase.Builder<AppDatabase>) {
        appDatabase = databaseBuilder
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
}