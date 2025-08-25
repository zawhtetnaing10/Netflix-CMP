package com.zg.netflixcmp.core.persistence

import com.zg.netflixcmp.movies.data.vos.MovieVO
import com.zg.netflixcmp.core.persistence.convertToPersistenceModel

internal class Database(private val sqlDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(driver = sqlDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    // Insert multiple movies
    fun insertMovies(movies: List<MovieVO>) {

        val moviePersistenceModels = movies.map {
            it.convertToPersistenceModel()
        }
        dbQuery.transaction {
            moviePersistenceModels.forEach {
                dbQuery.insertPartialMovie(
                    id = it.id,
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    genreIds = it.genreIds,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    title = it.title,
                    video = it.video,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount
                )
            }
        }
    }

    // Insert Single Movie
    fun insertSingleMovieFull(movie: MovieVO) {
        val moviePersistenceModel = movie.convertToPersistenceModel()
        with(moviePersistenceModel) {
            dbQuery.insertFullMovie(
                id = id,
                adult = adult,
                backdropPath = backdropPath,
                belongsToCollection = belongsToCollection,
                budget = budget,
                genres = genres,
                genreIds = genreIds,
                homePage = homePage,
                imdbId = imdbId,
                originCountry = originCountry,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                overview = overview,
                popularity = popularity,
                posterPath = posterPath,
                productionCompanies = productionCompanies,
                productionCountries = productionCountries,
                releaseDate = releaseDate,
                spokenLanguages = spokenLanguages,
                status = status,
                tagline = tagline,
                title = title,
                video = video,
                voteAverage = voteAverage,
                voteCount = voteCount
            )
        }

    }

    // Get Movie By Id
    fun getMovieById(movieId: Int): MovieVO? {
        val movie = dbQuery.getMovieById(movieId.toLong()).executeAsOneOrNull()
        return movie?.convertToMovieVO()
    }

    // Get First Movie
    fun getFirstMovie(): MovieVO? {
        val movie = dbQuery.getFirstMovie().executeAsOneOrNull()
        return movie?.convertToMovieVO()
    }
}