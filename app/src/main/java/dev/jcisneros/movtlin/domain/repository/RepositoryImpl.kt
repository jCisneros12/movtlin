package dev.jcisneros.movtlin.domain.repository

import dev.jcisneros.movtlin.data.datasource.network.IDataSource
import dev.jcisneros.movtlin.data.model.GenreModel
import dev.jcisneros.movtlin.data.model.MovieDetail
import dev.jcisneros.movtlin.data.model.MovieItem
import dev.jcisneros.movtlin.utils.Resource

class RepositoryImpl(private val networkDataSource: IDataSource): IRepository {
    override suspend fun getPopularMovies(): Resource<List<MovieItem>> {
        return networkDataSource.getPopularMovies()
    }

    override suspend fun getMovieById(movieId: String): Resource<MovieDetail> {
        return networkDataSource.getMovieById(movieId)
    }

    override suspend fun getMovieGenres(): Resource<List<GenreModel>> {
        return networkDataSource.getMovieGenres()
    }

    override suspend fun getMovieByTitle(title: String): Resource<MovieDetail> {
        return networkDataSource.getMovieByTitle(title)
    }

    override suspend fun getMoviesByGenre(genreId: String): Resource<List<MovieItem>> {
        return networkDataSource.getMoviesByGenre(genreId)
    }

    override suspend fun getRecommendedMovies(movieId: String): Resource<List<MovieItem>> {
        return networkDataSource.getRecommendedMovies(movieId)
    }
}