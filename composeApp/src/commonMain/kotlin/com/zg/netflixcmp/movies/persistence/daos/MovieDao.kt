package com.zg.netflixcmp.movies.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zg.netflixcmp.movies.data.vos.MovieVO
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieVO>)

    @Query("SELECT * FROM movies WHERE id = :movieId LIMIT 1")
    fun getMovieByIdObservable(movieId: Int): Flow<MovieVO?>

    @Query("SELECT * FROM movies WHERE id = :movieId LIMIT 1")
    fun getMovieById(movieId: Int): MovieVO?

    @Query("SELECT * FROM movies LIMIT 1")
    suspend fun getFeaturedMovie(): MovieVO?

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<MovieVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSingleMovie(movie: MovieVO)

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()
}