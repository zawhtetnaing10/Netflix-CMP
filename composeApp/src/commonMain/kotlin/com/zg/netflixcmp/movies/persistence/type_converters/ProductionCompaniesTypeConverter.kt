package com.zg.netflixcmp.movies.persistence.type_converters

import androidx.room.TypeConverter
import com.zg.netflixcmp.core.utils.universalJsonParser
import com.zg.netflixcmp.movies.data.vos.ProductionCompanyVO

class ProductionCompaniesTypeConverter {
    @TypeConverter
    fun fromProductionCompanies(productionCompanies: List<ProductionCompanyVO>?): String? {
        return productionCompanies?.let {
            universalJsonParser.encodeToString(it)
        }
    }

    @TypeConverter
    fun toProductionCompanies(json: String?): List<ProductionCompanyVO>? {
        return json?.let {
            universalJsonParser.decodeFromString(it)
        }
    }
}