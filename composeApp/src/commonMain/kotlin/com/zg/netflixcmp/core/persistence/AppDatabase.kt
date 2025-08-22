package com.zg.netflixcmp.core.persistence

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.movies.persistence.daos.MovieDao
import com.zg.netflixcmp.movies.persistence.type_converters.BelongsToCollectionTypeConverter
import com.zg.netflixcmp.movies.persistence.type_converters.GenreIdsTypeConverter
import com.zg.netflixcmp.movies.persistence.type_converters.GenreListTypeConverter
import com.zg.netflixcmp.movies.persistence.type_converters.OriginCountryTypeConverter
import com.zg.netflixcmp.movies.persistence.type_converters.ProductionCompaniesTypeConverter
import com.zg.netflixcmp.movies.persistence.type_converters.ProductionCountriesTypeConverter
import com.zg.netflixcmp.movies.persistence.type_converters.SpokenLanguagesTypeConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [
        MovieVO::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    BelongsToCollectionTypeConverter::class,
    GenreListTypeConverter::class,
    OriginCountryTypeConverter::class,
    ProductionCompaniesTypeConverter::class,
    ProductionCountriesTypeConverter::class,
    SpokenLanguagesTypeConverter::class,
    GenreIdsTypeConverter::class
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}