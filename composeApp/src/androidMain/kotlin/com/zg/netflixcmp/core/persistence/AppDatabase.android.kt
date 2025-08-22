package com.zg.netflixcmp.core.persistence

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilderAndroid(appContext: Context): RoomDatabase.Builder<AppDatabase> {
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}