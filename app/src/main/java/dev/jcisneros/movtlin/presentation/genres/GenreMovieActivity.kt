package dev.jcisneros.movtlin.presentation.genres

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dev.jcisneros.movtlin.R
import dev.jcisneros.movtlin.data.datasource.network.NetworkDataSourceImpl
import dev.jcisneros.movtlin.data.model.MovieItem
import dev.jcisneros.movtlin.databinding.ActivityGenreMovieBinding
import dev.jcisneros.movtlin.domain.repository.RepositoryImpl
import dev.jcisneros.movtlin.presentation.adapters.MoviesAdapter
import dev.jcisneros.movtlin.presentation.adapters.OnClickAdapter
import dev.jcisneros.movtlin.presentation.adapters.OnClickAdapter.Companion.GENRE_ID
import dev.jcisneros.movtlin.presentation.adapters.OnClickAdapter.Companion.GENRE_NAME
import dev.jcisneros.movtlin.utils.Resource
import dev.jcisneros.movtlin.utils.toast

class GenreMovieActivity : AppCompatActivity(), MoviesAdapter.OnItemClickListener {

    // viewBinding
    private lateinit var binding: ActivityGenreMovieBinding

    // viewModel
    private val viewModel by viewModels<GenreMovieViewModel> {
        GenreMovieViewModelFactory(
            RepositoryImpl(NetworkDataSourceImpl())
        )
    }

    // recycler adapter
    private val adapter: MoviesAdapter by lazy {
        MoviesAdapter(this, this)
    }

    // genre id
    private lateinit var genreId: String
    private lateinit var genreName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenreMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // extract the genre id from intent
        genreId = intent.getStringExtra(GENRE_ID).toString()
        genreName = intent.getStringExtra(GENRE_NAME).toString()

        // set adapter to recycler
        setRecyclerAdapter()

        // observe movies by genre
        setupObserveMoviesByGenre(genreId)
    }

    private fun setupObserveMoviesByGenre(genreId: String) {
        viewModel.getMoviesByGenre(genreId).observe(this, { movie ->
            when (movie) {
                is Resource.Loading -> {
                    binding.movieLoadingLottie.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.textGenreName.text = genreName
                    binding.movieLoadingLottie.visibility = View.GONE
                    adapter.setListData(movie.data)
                    adapter.notifyDataSetChanged()
                }
                is Resource.Failure -> {
                    this.toast(getString(R.string.server_error))
                }
            }
        })
    }

    private fun setRecyclerAdapter() {
        binding.recyclerMoviesByGenre.layoutManager = LinearLayoutManager(this)
        binding.recyclerMoviesByGenre.adapter = adapter
    }

    override fun onClickItem(movie: MovieItem) {
        OnClickAdapter.onClickMovieItem(movie.movieId.toString(), this)
    }
}
