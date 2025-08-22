package com.zg.netflixcmp.movies.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zg.netflixcmp.core.utils.FEATURED_MOVIE_IMAGE_BASE_URL
import com.zg.netflixcmp.core.utils.GENERAL_MOVIE_IMAGE_BASE_URL
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "movies")
@Serializable
data class MovieVO(
    @SerialName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerialName("adult")
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollectionVO?,
    @SerialName("budget")
    val budget: Long?,
    @SerialName("genres")
    val genres: List<GenreVO>?,
    @SerialName("homepage")
    val homepage: String?,
    @SerialName("imdb_id")
    val imdbId: String?,
    @SerialName("origin_country")
    val originCountry: List<String>?,
    @SerialName("genre_ids")
    val genreIds: List<Int>?,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("popularity")
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyVO>?,

    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryVO>?,

    @SerialName("release_date")
    val releaseDate: String,

    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageVO>?,

    @SerialName("status")
    val status: String?,

    @SerialName("tagline")
    val tagline: String?,

    @SerialName("title")
    val title: String,
    @SerialName("video")
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
) {
    fun getFullPosterPath() : String{
        return "$FEATURED_MOVIE_IMAGE_BASE_URL$posterPath"
    }

    fun getFullBackdropPath() : String {
        return "$GENERAL_MOVIE_IMAGE_BASE_URL$backdropPath"
    }
}