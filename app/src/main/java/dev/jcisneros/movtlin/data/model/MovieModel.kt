package dev.jcisneros.movtlin.data.model

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("id")
    val movieId: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("poster_path")
    val posterPath: String?,
)

data class MovieDetail(
    @SerializedName("id")
    val movieId: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("original_language")
    val language: String?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("genres")
    val genres: List<GenreModel>,
)

data class MovieList(
    @SerializedName("results")
    val movieList: List<MovieItem>?
)