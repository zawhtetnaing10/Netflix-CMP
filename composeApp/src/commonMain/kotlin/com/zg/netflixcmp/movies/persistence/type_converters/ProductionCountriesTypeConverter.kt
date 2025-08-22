package com.zg.netflixcmp.movies.persistence.type_converters

import androidx.room.TypeConverter
import com.zg.netflixcmp.core.utils.universalJsonParser
import com.zg.netflixcmp.movies.data.vos.ProductionCountryVO

class ProductionCountriesTypeConverter {
    @TypeConverter
    fun fromProductionCountries(productionCountries : List<ProductionCountryVO>?) : String? {
        return productionCountries?.let {
            universalJsonParser.encodeToString(it)
        }
    }

    @TypeConverter
    fun toProductionCountries(json : String?) : List<ProductionCountryVO>?{
        return json?.let {
            universalJsonParser.decodeFromString(it)
        }
    }
}