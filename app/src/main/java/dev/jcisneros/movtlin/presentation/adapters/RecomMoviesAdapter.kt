package dev.jcisneros.movtlin.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.jcisneros.movtlin.data.model.MovieItem
import dev.jcisneros.movtlin.databinding.ItemMovieRecommendedBinding
import dev.jcisneros.movtlin.utils.Const


class RecomMoviesAdapter(
    private val context: Context,
    private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<RecomMoviesAdapter.MoviesRecomViewHolder>() {

    //movie list
    private var movieList: List<MovieItem> = arrayListOf()

    fun setListData(data: List<MovieItem>) {
        movieList = data
    }

    //create item for recycler list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesRecomViewHolder {
        val itemBinding = ItemMovieRecommendedBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return MoviesRecomViewHolder(itemBinding)
    }

    //bind item with movie data
    override fun onBindViewHolder(holder: MoviesRecomViewHolder, position: Int) {
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

    inner class MoviesRecomViewHolder(private val binding: ItemMovieRecommendedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(movie: MovieItem) {
            //onCLick listeners
            binding.itemRecommMovie.setOnClickListener { itemClickListener.onClickItem(movie) }
            //set data to view
            binding.titleRecommended.text = movie.title.toString()
            binding.averageRecommended.text = movie.voteAverage.toString()
            binding.votesRecommended.text = movie.voteCount.toString() + " votes"
            binding.overviewRecommended.text = movie.overview.toString()
            Glide.with(context).load(Const.BASE_URL_IMAGE + movie.posterPath)
                .into(binding.posterImageRecommended)
        }
    }
}