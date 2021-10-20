package dev.jcisneros.movtlin.presentation.search

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dev.jcisneros.movtlin.data.datasource.network.NetworkDataSourceImpl
import dev.jcisneros.movtlin.databinding.ActivitySearchBinding
import dev.jcisneros.movtlin.domain.repository.RepositoryImpl
import dev.jcisneros.movtlin.utils.Resource

class SearchActivity : AppCompatActivity() {

    // viewBinding
    private lateinit var binding: ActivitySearchBinding

    // viewModel
    private val viewModel by viewModels<SearchViewModel> {
        SearchMovieViewModelFactory(RepositoryImpl(NetworkDataSourceImpl()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // button search onClick method
        binding.buttonSearch.setOnClickListener {
            setupSearchMovie()
        }
    }

    private fun setupSearchMovie() {
        var movie = binding.inputSearchMovie.text.toString()
        if (movie.isNotEmpty()) {
            viewModel.searchMovieByName(movie).observe(this, { res ->
                when (res) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        // todo: set movie data
                    }
                    is Resource.Failure -> {}
                }
            })
        }
    }
}
