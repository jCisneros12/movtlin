package dev.jcisneros.movtlin.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.jcisneros.movtlin.data.model.GenreModel
import dev.jcisneros.movtlin.data.model.MovieItem
import dev.jcisneros.movtlin.databinding.ItemGenreBinding

class GenresAdapter(
    private val context: Context,
    private val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    //genre list
    private var genreList: List<GenreModel> = arrayListOf()

    fun setListData(data: List<GenreModel>) {
        genreList = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val itemBinding = ItemGenreBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return GenresViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val genre: GenreModel = genreList[position]
        holder.bindView(genre)
    }

    override fun getItemCount(): Int {
        return if (genreList.isNotEmpty()) genreList.size
        else 0
    }

    //interface for implements onClick methods
    interface OnItemClickListener {
        fun onClickItem(genre: GenreModel)
        //add more methods here...
    }

    inner class GenresViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(genre: GenreModel) {
            binding.itemTxtGenre.setOnClickListener { itemClickListener.onClickItem(genre) }
            binding.itemTxtGenre.text = genre.name.toString()
        }
    }


}