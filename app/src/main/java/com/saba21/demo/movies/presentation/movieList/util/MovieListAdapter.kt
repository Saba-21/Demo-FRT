package com.saba21.demo.movies.presentation.movieList.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.saba21.demo.domain.models.MovieModel
import com.saba21.demo.movies.R
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var movieList: List<MovieModel> = emptyList()
    private var onClick: ((MovieModel) -> Unit) = {}

    fun setOnClick(onClick: (MovieModel) -> Unit) {
        this.onClick = onClick
    }

    fun bindData(movieList: List<MovieModel>) {
        this.movieList = movieList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movieList[position]
        holder.itemView.setOnClickListener {
            onClick(item)
        }
        Glide.with(holder.itemView.context)
            .load(item.posterUrl)
            .error(R.drawable.shape_image_placeholder)
            .placeholder(R.drawable.shape_image_placeholder)
            .transform(CenterCrop(), RoundedCorners(24))
            .into(holder.itemView.ivPoster)
    }
}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view)