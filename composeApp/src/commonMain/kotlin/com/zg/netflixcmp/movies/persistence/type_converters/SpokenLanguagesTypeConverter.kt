package com.zg.netflixcmp.movies.persistence.type_converters

import androidx.room.TypeConverter
import com.zg.netflixcmp.core.utils.universalJsonParser
import com.zg.netflixcmp.movies.data.vos.SpokenLanguageVO

class SpokenLanguagesTypeConverter {
    @TypeConverter
    fun fromSpokenLanguages(spokenLanguages: List<SpokenLanguageVO>?): String? {
        return spokenLanguages?.let {
            universalJsonParser.encodeToString(it)
        }
    }

    @TypeConverter
    fun toSpokenLanguages(json: String?): List<SpokenLanguageVO>? {
        return json?.let {
            universalJsonParser.decodeFromString(it)
        }
    }
}