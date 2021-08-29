package dev.jcisneros.movtlin.data.datasource.network

import dev.jcisneros.movtlin.data.model.GenreModel
import dev.jcisneros.movtlin.data.model.MovieDetail
import dev.jcisneros.movtlin.data.model.MovieItem
import dev.jcisneros.movtlin.utils.Resource
import dev.jcisneros.movtlin.utils.RetrofitClient

class NetworkDataSourceImpl: IDataSource {

    //get popular movies
    override suspend fun getPopularMovies(): Resource<List<MovieItem>> {
        return Resource.Success(RetrofitClient.webservice.getPopularMovies().movieList!!)
    }

    //get movie By id
    override suspend fun getMovieById(movieId: String): Resource<MovieDetail> {
        return Resource.Success(RetrofitClient.webservice.getMovieById(movieId))
    }

    override suspend fun getMovieGenres(): Resource<List<GenreModel>> {
        return Resource.Success(RetrofitClient.webservice.getMovieGenres().genreList!!)
    }

    override suspend fun getMovieByTitle(title: String): Resource<MovieDetail> {
        return Resource.Success(RetrofitClient.webservice.getMovieByTitle(title))
    }

    override suspend fun getMoviesByGenre(genreId: String): Resource<List<MovieItem>> {
        return Resource.Success(RetrofitClient.webservice.getMoviesByGenre(genreId).movieList!!)
    }

    override suspend fun getRecommendedMovies(movieId: String): Resource<List<MovieItem>> {
        return Resource.Success(RetrofitClient.webservice.getRecommendedMovies(movieId).movieList!!)
    }
}