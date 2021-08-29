package dev.jcisneros.movtlin.domain

import dev.jcisneros.movtlin.data.model.GenreList
import dev.jcisneros.movtlin.data.model.MovieDetail
import dev.jcisneros.movtlin.data.model.MovieList
import dev.jcisneros.movtlin.utils.Const.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    //get popular movies
    @GET("movie/popular?api_key=${API_KEY}&language=en-US&page=1")
    suspend fun getPopularMovies(): MovieList

    //get movie by id
    @GET("movie/{movieId}?api_key=${API_KEY}&language=en-US")
    suspend fun getMovieById(@Path("movieId", encoded = true) movieId: String): MovieDetail

    //get movie genres
    @GET("genre/movie/list?api_key=${API_KEY}&language=en-US")
    suspend fun getMovieGenres(): GenreList

    //get movie by title
    @GET("search/movie?api_key=${API_KEY}&language=en-US&query={title}")//ex: movie%name
    suspend fun getMovieByTitle(@Path("title", encoded = true) title: String): MovieDetail

    //get movies by genre id
    @GET("discover/movie?api_key=${API_KEY}&with_genres={genreId}")
    suspend fun getMoviesByGenre(@Path("genreId", encoded = true) genreId: String): MovieList

    //get recommended movies by movie id
    @GET("movie/{movieId}/recommendations?api_key=${API_KEY}&language=en-US&page=1")
    suspend fun getRecommendedMovies(@Path("movieId", encoded = true) movieId: String): MovieList

}