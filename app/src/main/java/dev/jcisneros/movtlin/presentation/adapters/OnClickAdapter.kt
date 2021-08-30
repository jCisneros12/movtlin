package dev.jcisneros.movtlin.presentation.adapters

import android.content.Context
import android.content.Intent

import dev.jcisneros.movtlin.presentation.details.MovieDetailsActivity
import dev.jcisneros.movtlin.presentation.movies.MoviesFragment

class OnClickAdapter {
    companion object {
        //message name
        const val MOVIE_ID = "dev.jcisneros.movtlin.movieId"

        fun onClickMovieItem(movieId: String, context: Context){
            val intent = Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
            context.startActivity(intent)
        }
    }
}