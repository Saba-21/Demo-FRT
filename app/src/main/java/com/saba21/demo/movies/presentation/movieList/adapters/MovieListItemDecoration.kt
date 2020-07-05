package com.saba21.demo.movies.presentation.movieList.adapters

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.saba21.demo.movies.R

class MovieListItemDecoration(val context: Context) : DividerItemDecoration(context, HORIZONTAL) {

    init {
        setDrawable(ContextCompat.getDrawable(context, R.drawable.shape_movie_item_divider)!!)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: androidx.recyclerview.widget.RecyclerView,
        state: androidx.recyclerview.widget.RecyclerView.State
    ) {
        outRect.bottom = context.resources.getDimensionPixelOffset(R.dimen.movie_item_divider_size) / 2

        if (parent.getChildAdapterPosition(view) == 0 || parent.getChildAdapterPosition(view) == 1) {
            outRect.top = context.resources.getDimensionPixelOffset(R.dimen.movie_item_divider_size) / 2
        }

        if ((parent.getChildAdapterPosition(view) % 2) == 0) {
            outRect.right = context.resources.getDimensionPixelOffset(R.dimen.movie_item_divider_size) / 4
            outRect.left = context.resources.getDimensionPixelOffset(R.dimen.movie_item_divider_size) / 2
        } else {
            outRect.left = context.resources.getDimensionPixelOffset(R.dimen.movie_item_divider_size) / 4
            outRect.right = context.resources.getDimensionPixelOffset(R.dimen.movie_item_divider_size) / 2
        }

    }
}