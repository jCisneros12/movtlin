package dev.jcisneros.movtlin.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.jcisneros.movtlin.data.model.MovieItem
import dev.jcisneros.movtlin.databinding.ItemMovieBinding
import dev.jcisneros.movtlin.utils.Const.BASE_URL_IMAGE

class MoviesAdapter(
    private val context: Context,
    private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    //movie list
    private var movieList: List<MovieItem> = arrayListOf()

    fun setListData(data: List<MovieItem>) {
        movieList = data
    }

    //create item for recycler list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemBinding = ItemMovieBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return MoviesViewHolder(itemBinding)
    }

    //bind item with movie data
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie: MovieItem = movieList[position]
        holder.bindView(movie)
    }

    override fun getItemCount(): Int {
        return if (movieList.isNotEmpty()) movieList.size
        else 0
    }

    //interface for implements onClick methods
    interface OnItemClickListener {
        fun onClickItem(movie: MovieItem)
        //add more methods here...
    }

    inner class MoviesViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(movie: MovieItem) {
            //onCLick listeners
            binding.itemMovie.setOnClickListener { itemClickListener.onClickItem(movie) }
            //set data to view
            binding.itemMovieTitle.text = movie.title.toString()
            binding.itemMovieAverage.text = movie.voteAverage.toString()
            binding.itemMovieVotes.text = movie.voteCount.toString() + " votes"
            binding.itemMovieOverview.text = movie.overview.toString()
            Glide.with(context).load(BASE_URL_IMAGE + movie.posterPath).into(binding.moviePoster)
        }
    }
}