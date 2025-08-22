package com.zg.netflixcmp.movies.persistence.type_converters

import androidx.room.TypeConverter
import com.zg.netflixcmp.core.utils.universalJsonParser

class OriginCountryTypeConverter {
    @TypeConverter
    fun fromOriginCountry(originCountry: List<String>?): String? {
        return originCountry?.let {
            universalJsonParser.encodeToString(it)
        }
    }

    @TypeConverter
    fun toOriginCountry(json: String?): List<String>? {
        return json?.let {
            universalJsonParser.decodeFromString(it)
        }
    }
}