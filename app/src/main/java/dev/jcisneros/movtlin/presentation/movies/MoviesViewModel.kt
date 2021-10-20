package dev.jcisneros.movtlin.presentation.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import dev.jcisneros.movtlin.domain.repository.IRepository
import dev.jcisneros.movtlin.utils.Resource
import kotlinx.coroutines.Dispatchers

class MoviesViewModel(private val repository: IRepository) : ViewModel() {

    //get popular movies from repo
    val popularMovies = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repository.getPopularMovies())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
            Log.e("POPULAR-MOV-ERR", e.toString())
        }
    }

    // get all genres
    val genreList = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repository.getMovieGenres())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
            Log.e("GENRE-LIST-ERR", e.toString())
        }
    }

}

@Suppress("UNCHECKED_CAST")
class MoviesViewModelFactory(private val repository: IRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        (MoviesViewModel(repository) as T)
}