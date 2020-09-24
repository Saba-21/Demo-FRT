package com.saba21.demo.movies.custom.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
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
        val diffUtilCallback = MovieListDIffUtil(this.movieList, movieList)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback, true)
        diffUtilResult.dispatchUpdatesTo(this)
        this.movieList = movieList
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

class MovieListDIffUtil(
    private val oldList: List<MovieModel>,
    private val newList: List<MovieModel>
) : DiffUtil.Callback() {

    override fun getOldListSize():
            Int = oldList.size

    override fun getNewListSize():
            Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int):
            Boolean = oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int):
            Boolean = oldList[oldItemPosition] == (newList[newItemPosition])

}