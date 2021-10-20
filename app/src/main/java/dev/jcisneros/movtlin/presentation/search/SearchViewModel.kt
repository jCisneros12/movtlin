package dev.jcisneros.movtlin.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import dev.jcisneros.movtlin.domain.repository.IRepository
import dev.jcisneros.movtlin.utils.Resource
import kotlinx.coroutines.Dispatchers

class SearchViewModel(private val repository: IRepository) : ViewModel() {

    fun searchMovieByName(title: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repository.getMovieByTitle(title))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
            Log.e("SEARCH-MOVIE-ERROR", e.toString())
        }
    }
}

@Suppress("UNCHECKED_CAST")
class SearchMovieViewModelFactory(private val repository: IRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        (SearchViewModel(repository) as T)
}
