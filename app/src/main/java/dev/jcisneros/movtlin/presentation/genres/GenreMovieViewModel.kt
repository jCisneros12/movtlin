package dev.jcisneros.movtlin.presentation.genres

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import dev.jcisneros.movtlin.domain.repository.IRepository
import dev.jcisneros.movtlin.presentation.details.MovieDetailsViewModel
import dev.jcisneros.movtlin.utils.Resource
import kotlinx.coroutines.Dispatchers

class GenreMovieViewModel(private val repository: IRepository): ViewModel() {

    fun getMoviesByGenre(genreId: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repository.getMoviesByGenre(genreId))
        }catch (e: Exception) {
            emit(Resource.Failure(e))
            Log.e("MOVIES-GENRE-ERR", e.toString())
        }
    }

}


@Suppress("UNCHECKED_CAST")
class GenreMovieViewModelFactory(private val repository: IRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        (GenreMovieViewModel(repository) as T)
}