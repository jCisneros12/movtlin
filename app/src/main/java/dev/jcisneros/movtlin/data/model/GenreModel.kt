package dev.jcisneros.movtlin.data.model

import com.google.gson.annotations.SerializedName

data class GenreModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)

data class GenreList(
    @SerializedName("genres")
    val genreList: List<GenreModel>?
)