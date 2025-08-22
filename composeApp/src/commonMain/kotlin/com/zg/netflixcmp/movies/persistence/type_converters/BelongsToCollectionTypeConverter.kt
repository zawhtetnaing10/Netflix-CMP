package com.zg.netflixcmp.movies.persistence.type_converters

import androidx.room.TypeConverter
import com.zg.netflixcmp.core.utils.universalJsonParser
import com.zg.netflixcmp.movies.data.vos.BelongsToCollectionVO
import kotlinx.serialization.json.Json

class BelongsToCollectionTypeConverter {
    @TypeConverter
    fun fromBelongsToCollection(belongsToCollectionVO: BelongsToCollectionVO?): String? {
        return belongsToCollectionVO?.let {
            universalJsonParser.encodeToString(belongsToCollectionVO)
        }
    }

    fun toBelongsToCollection(jsonString: String?): BelongsToCollectionVO? {
        return jsonString?.let {
            universalJsonParser.decodeFromString(jsonString)
        }
    }
}