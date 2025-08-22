package com.zg.netflixcmp.movies.persistence.type_converters

import androidx.room.TypeConverter
import com.zg.netflixcmp.core.utils.universalJsonParser
import com.zg.netflixcmp.movies.data.vos.GenreVO

class GenreListTypeConverter {
    @TypeConverter
    fun fromGenreList(genreList: List<GenreVO>?): String? {
        return genreList?.let {
            universalJsonParser.encodeToString(genreList)
        }
    }

    @TypeConverter
    fun toGenreList(json: String?): List<GenreVO>? {
        return json?.let {
            universalJsonParser.decodeFromString(it)
        }
    }
}