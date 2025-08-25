package com.zg.netflixcmp.core.persistence

import com.zg.netflixcmp.core.utils.universalJsonParser
import com.zg.netflixcmp.movies.data.vos.BelongsToCollectionVO
import com.zg.netflixcmp.movies.data.vos.GenreVO
import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.movies.data.vos.ProductionCompanyVO
import com.zg.netflixcmp.movies.data.vos.ProductionCountryVO
import com.zg.netflixcmp.movies.data.vos.SpokenLanguageVO

// Convert to persistence model
fun MovieVO.convertToPersistenceModel(): Movie {
    val belongsToCollectionJsonString = universalJsonParser.encodeToString(this.belongsToCollection)
    val genresJsonString = universalJsonParser.encodeToString(this.genres)
    val genreIdsJsonString = universalJsonParser.encodeToString(this.genreIds)
    val productionCompaniesJsonString = universalJsonParser.encodeToString(this.productionCompanies)
    val productionCountriesJsonString = universalJsonParser.encodeToString(this.productionCountries)
    val spokenLanguagesJsonString = universalJsonParser.encodeToString(this.spokenLanguages)

    return Movie(
        id = this.id.toLong(),
        adult = this.adult,
        backdropPath = this.backdropPath,
        belongsToCollection = belongsToCollectionJsonString,
        budget = this.budget,
        genres = genresJsonString,
        homePage = this.homepage,
        imdbId = this.imdbId,
        originCountry = this.originalCountry,
        genreIds = genreIdsJsonString,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        productionCompanies = productionCompaniesJsonString,
        productionCountries = productionCountriesJsonString,
        releaseDate = this.releaseDate,
        spokenLanguages = spokenLanguagesJsonString,
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount.toLong(),
    )
}

// Convert Movie to MovieVO (Persistence => Data)
fun Movie.convertToMovieVO(): MovieVO {

    val belongsToCollectionVO : BelongsToCollectionVO? =
        if (belongsToCollection != null) universalJsonParser.decodeFromString(this.belongsToCollection) else null

    val genresVO : List<GenreVO>? = if (genres != null) universalJsonParser.decodeFromString(this.genres) else null

    val genreIdsVO: List<Int>? =
        if (genreIds != null) universalJsonParser.decodeFromString(this.genreIds) else null

    val productionCompaniesVO : List<ProductionCompanyVO>?=
        if (productionCompanies != null) universalJsonParser.decodeFromString(this.productionCompanies) else null

    val productionCountriesVO : List<ProductionCountryVO>? =
        if (productionCountries != null) universalJsonParser.decodeFromString(this.productionCountries) else null

    val spokenLanguagesVO: List<SpokenLanguageVO>? =
        if (spokenLanguages != null) universalJsonParser.decodeFromString(this.spokenLanguages) else null

    return MovieVO(
        id = this.id.toInt(),
        adult = this.adult ?: false,
        backdropPath = this.backdropPath,
        belongsToCollection = belongsToCollectionVO,
        budget = this.budget,
        genres = genresVO,
        homepage = this.homePage,
        imdbId = this.imdbId,
        originalCountry = this.originCountry,
        genreIds = genreIdsVO,
        originalLanguage = this.originalLanguage ?: "",
        originalTitle = this.originalTitle ?: "",
        overview = this.overview ?: "",
        popularity = this.popularity ?: 0.0,
        posterPath = this.posterPath ?: "",
        productionCompanies = productionCompaniesVO,
        productionCountries = productionCountriesVO,
        releaseDate = this.releaseDate ?: "",
        spokenLanguages = spokenLanguagesVO,
        status = this.status,
        tagline = this.tagline,
        title = this.title ?: "",
        video = this.video ?: false,
        voteAverage = this.voteAverage ?: 0.0,
        voteCount = this.voteCount?.toInt() ?: 0
    )
}

