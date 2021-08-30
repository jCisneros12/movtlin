package dev.jcisneros.movtlin.presentation.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import dev.jcisneros.movtlin.domain.repository.IRepository
import dev.jcisneros.movtlin.presentation.movies.MoviesViewModel
import dev.jcisneros.movtlin.utils.Resource
import kotlinx.coroutines.Dispatchers

class MovieDetailsViewModel(private val repository: IRepository): ViewModel() {

    //get movie details by genre id
    fun movieDetails(genreId: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repository.getMovieById(genreId))
        }catch (e: Exception){
            emit(Resource.Failure(e))
            Log.e("MOVIE-DETAILS-ERR", e.toString())
        }
    }

    //get recommended movies by movie id
    fun recommendedMovies(movieId: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repository.getRecommendedMovies(movieId))
        }catch (e: Exception){
            emit(Resource.Failure(e))
            Log.e("MOVIES-RECOMMENDED-ERR", e.toString())
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MovieDetailsViewModelFactory(private val repository: IRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        (MovieDetailsViewModel(repository) as T)
}