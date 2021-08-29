package dev.jcisneros.movtlin.domain.repository

import dev.jcisneros.movtlin.data.model.GenreModel
import dev.jcisneros.movtlin.data.model.MovieDetail
import dev.jcisneros.movtlin.data.model.MovieItem
import dev.jcisneros.movtlin.utils.Resource

interface IRepository {
    suspend fun getPopularMovies(): Resource<List<MovieItem>>
    suspend fun getMovieById(movieId: String): Resource<MovieDetail>
    suspend fun getMovieGenres(): Resource<List<GenreModel>>
    suspend fun getMovieByTitle(title: String): Resource<MovieDetail>
    suspend fun getMoviesByGenre(genreId: String): Resource<List<MovieItem>>
    suspend fun getRecommendedMovies(movieId: String): Resource<List<MovieItem>>
}