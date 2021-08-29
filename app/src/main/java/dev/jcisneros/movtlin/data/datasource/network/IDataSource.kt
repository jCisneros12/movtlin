package dev.jcisneros.movtlin.data.datasource.network

import dev.jcisneros.movtlin.data.model.GenreModel
import dev.jcisneros.movtlin.data.model.MovieDetail
import dev.jcisneros.movtlin.data.model.MovieItem
import dev.jcisneros.movtlin.utils.Resource

interface IDataSource {
    suspend fun getPopularMovies(): Resource<List<MovieItem>>
    suspend fun getMovieById(movieId: String): Resource<MovieDetail>
    suspend fun getMovieGenres(): Resource<List<GenreModel>>
    suspend fun getMovieByTitle(title: String): Resource<MovieDetail>
    suspend fun getMoviesByGenre(genreId: String): Resource<List<MovieItem>>
    suspend fun getRecommendedMovies(movieId: String): Resource<List<MovieItem>>
}