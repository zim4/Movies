package com.adzmf.thelistofmovies.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adzmf.thelistofmovies.core.R
import com.adzmf.thelistofmovies.core.databinding.ItemListBinding
import com.adzmf.thelistofmovies.core.domain.model.Movie
import com.adzmf.thelistofmovies.core.utils.Utils
import com.bumptech.glide.Glide
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListBinding.bind(itemView)
        fun bind(data: Movie) {
            with(binding) {
                imgBroken.visibility = View.GONE
                pbPoster.visibility = View.VISIBLE
                if (data.posterPath == "-") {
                    imgBroken.visibility = View.VISIBLE
                    pbPoster.visibility = View.GONE
                }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500${data.posterPath}")
                    .into(imgPoster)

                rating.rating = (data.voteAverage / 2.0).toFloat()
                tvTitle.text = data.title
                tvReleasedOn.text = itemView.context.resources.getString(R.string.released_on, Utils.dateFormatter(itemView.context, data.releaseDate))
                tvOverview.text = data.overview
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}