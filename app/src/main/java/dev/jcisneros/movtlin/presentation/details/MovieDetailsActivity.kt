package dev.jcisneros.movtlin.presentation.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dev.jcisneros.movtlin.R
import dev.jcisneros.movtlin.data.datasource.network.NetworkDataSourceImpl
import dev.jcisneros.movtlin.data.model.MovieItem
import dev.jcisneros.movtlin.databinding.ActivityMovieDetailsBinding
import dev.jcisneros.movtlin.domain.repository.RepositoryImpl
import dev.jcisneros.movtlin.presentation.adapters.MoviesAdapter
import dev.jcisneros.movtlin.presentation.adapters.OnClickAdapter
import dev.jcisneros.movtlin.presentation.adapters.OnClickAdapter.Companion.MOVIE_ID
import dev.jcisneros.movtlin.presentation.adapters.RecomMoviesAdapter
import dev.jcisneros.movtlin.utils.Const
import dev.jcisneros.movtlin.utils.Resource
import dev.jcisneros.movtlin.utils.toast

class MovieDetailsActivity : AppCompatActivity(), RecomMoviesAdapter.OnItemClickListener {

    //viewBinding
    private lateinit var binding: ActivityMovieDetailsBinding

    //ViewModel
    private val viewModel by viewModels<MovieDetailsViewModel> {
        MovieDetailsViewModelFactory(
            RepositoryImpl(NetworkDataSourceImpl())
        )
    }

    //Recycler adapter for Movies
    private val adapterMovies: RecomMoviesAdapter by lazy {
        RecomMoviesAdapter(this, this)
    }

    //movie id
    private lateinit var movieId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //extract the movie id from intent
        movieId = intent.getStringExtra(MOVIE_ID).toString()
        //init adapters config
        setRecyclerAdapters()
        //observe movie details
        setupObserveMovieDetails(movieId)
        //observe recommended movie data
        setupObserveMoviesByGenre(movieId)
        //onBack pressed
        binding.movieDetailArrowBack.setOnClickListener { onBackPressed() }
    }

    //observe movie details
    private fun setupObserveMovieDetails(movieId: String) {
        viewModel.movieDetails(movieId).observe(this, { movie ->
            when (movie) {
                is Resource.Loading -> {
                    //TODO: show any ui for loading "state"
                }
                is Resource.Success -> {
                    Glide.with(this).load(Const.BASE_URL_IMAGE + movie.data.backdropPath)
                        .into(binding.movieDetailBackdropImage)
                    Glide.with(this).load(Const.BASE_URL_IMAGE + movie.data.posterPath)
                        .into(binding.movieDetailPoster)
                    binding.movieDetailTitle.text = movie.data.title.toString()
                    binding.genreOne.text = movie.data.genres[0].name.toString()
                    if (movie.data.genres.size > 1)
                        binding.genreTwo.text = movie.data.genres[1].name.toString()
                    binding.movieDetailRate.text = movie.data.voteAverage.toString()
                    binding.movieDetailRelease.text = movie.data.releaseDate.toString()
                    binding.movieDetailLanguage.text = movie.data.language.toString()
                    binding.movieDetailOverview.text = movie.data.overview.toString()
                }
                is Resource.Failure -> {
                    this.toast(getString(R.string.server_error))
                }
            }
        })
    }

    //observe recommended movies
    private fun setupObserveMoviesByGenre(movieId: String) {
        viewModel.recommendedMovies(movieId).observe(this, { movie ->
            when (movie) {
                is Resource.Loading -> {
                    //TODO: show any ui for loading "state"
                }
                is Resource.Success -> {
                    adapterMovies.setListData(movie.data)
                    adapterMovies.notifyDataSetChanged()
                }
                is Resource.Failure -> {
                    this.toast(getString(R.string.server_error))
                }
            }
        })
    }

    private fun setRecyclerAdapters() {
        //movies adapter
        binding.recyclerRecommendedMovies.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerRecommendedMovies.adapter = adapterMovies
    }

    //onClick methods to recycler movies
    override fun onClickItem(movie: MovieItem) {
        OnClickAdapter.onClickMovieItem(movie.movieId.toString(), this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}