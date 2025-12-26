package com.zg.netflixcmp.core.persistence

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    val context : Context
){
    actual fun create() : RoomDatabase.Builder<AppDatabase>{
        val dbFile = context.getDatabasePath("my_room.db")
        return Room.databaseBuilder<AppDatabase>(
            context = context,
            name = dbFile.absolutePath
        )
    }
}