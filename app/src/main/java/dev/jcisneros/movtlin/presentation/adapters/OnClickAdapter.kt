package dev.jcisneros.movtlin.presentation.adapters

import android.content.Context
import android.content.Intent
import dev.jcisneros.movtlin.presentation.details.MovieDetailsActivity
import dev.jcisneros.movtlin.presentation.genres.GenreMovieActivity

class OnClickAdapter {
    companion object {
        // message name
        const val MOVIE_ID = "dev.jcisneros.movtlin.movieId"
        const val GENRE_ID = "dev.jcisneros.movtlin.genreId"
        const val GENRE_NAME = "dev.jcisneros.movtlin.genreName"

        fun onClickMovieItem(movieId: String, context: Context) {
            val intent = Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
            context.startActivity(intent)
        }

        fun onClickGenreItem(genreId: String, genreName: String, context: Context) {
            val intent = Intent(context, GenreMovieActivity::class.java).apply {
                putExtra(GENRE_ID, genreId)
                putExtra(GENRE_NAME, genreName)
            }
            context.startActivity(intent)
        }
    }
}
