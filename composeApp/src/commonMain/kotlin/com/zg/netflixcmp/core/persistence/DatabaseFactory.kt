package com.zg.netflixcmp.core.persistence

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<AppDatabase>
}