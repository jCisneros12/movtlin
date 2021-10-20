package dev.jcisneros.movtlin.presentation.movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dev.jcisneros.movtlin.R
import dev.jcisneros.movtlin.data.datasource.network.NetworkDataSourceImpl
import dev.jcisneros.movtlin.data.model.GenreModel
import dev.jcisneros.movtlin.data.model.MovieItem
import dev.jcisneros.movtlin.databinding.FragmentMoviesBinding
import dev.jcisneros.movtlin.domain.repository.RepositoryImpl
import dev.jcisneros.movtlin.presentation.adapters.GenresAdapter
import dev.jcisneros.movtlin.presentation.adapters.MoviesAdapter
import dev.jcisneros.movtlin.presentation.adapters.OnClickAdapter
import dev.jcisneros.movtlin.utils.Resource
import dev.jcisneros.movtlin.utils.toast

class MoviesFragment :
    Fragment(),
    MoviesAdapter.OnItemClickListener,
    GenresAdapter.OnItemClickListener {

    // viewBinding
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    // ViewModel
    private val viewModel by viewModels<MoviesViewModel> {
        MoviesViewModelFactory(
            RepositoryImpl(NetworkDataSourceImpl())
        )
    }

    // Recycler adapter for Movies
    private val adapterMovies: MoviesAdapter by lazy {
        MoviesAdapter(requireContext(), this)
    }

    // Recycler adapter for Genres
    private val adapterGenres: GenresAdapter by lazy {
        GenresAdapter(requireContext(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recycler adapter
        setRecyclerAdapters()
        // set movie data to recycler
        setupObservePopularMovies()
        // set genre data to recycler
        setupObserveGenres()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupObservePopularMovies() {
        viewModel.popularMovies.observe(viewLifecycleOwner, { movie ->
            when (movie) {
                is Resource.Loading -> {
                    binding.movieLoadingLottie.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.movieLoadingLottie.visibility = View.GONE
                    adapterMovies.setListData(movie.data)
                    adapterMovies.notifyDataSetChanged()
                }
                is Resource.Failure -> {
                    requireActivity().toast(getString(R.string.server_error))
                }
            }
        })
    }

    private fun setupObserveGenres() {
        viewModel.genreList.observe(viewLifecycleOwner, { genre ->
            when (genre) {
                is Resource.Loading -> {
                    // TODO: show ui
                }
                is Resource.Success -> {
                    adapterGenres.setListData(genre.data)
                    adapterGenres.notifyDataSetChanged()
                }
                is Resource.Failure -> {
                    requireActivity().toast(getString(R.string.server_error))
                }
            }
        })
    }

    private fun setRecyclerAdapters() {
        // movies adapter
        binding.recyclerMovies.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerMovies.adapter = adapterMovies
        // genres adapter
        binding.recyclerGenres.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerGenres.adapter = adapterGenres
    }

    // onClick methods to recycler movies
    override fun onClickItem(movie: MovieItem) {
        OnClickAdapter.onClickMovieItem(movie.movieId.toString(), requireContext())
    }

    // onClick method for genre item
    override fun onClickItem(genre: GenreModel) {
        OnClickAdapter.onClickGenreItem(genre.id.toString(), genre.name.toString(), requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
