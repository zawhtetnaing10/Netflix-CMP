package com.zg.netflixcmp.movies.persistence.type_converters

import androidx.room.TypeConverter
import com.zg.netflixcmp.core.utils.universalJsonParser

class GenreIdsTypeConverter {
    @TypeConverter
    fun fromGenreIds(genreIds : List<Int>?) : String? {
        return genreIds?.let {
            universalJsonParser.encodeToString(it)
        }
    }

    @TypeConverter
    fun toGenreIds(json : String?) : List<Int>? {
        return json?.let {
            universalJsonParser.decodeFromString(it)
        }
    }
}