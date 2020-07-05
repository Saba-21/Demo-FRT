package com.saba21.demo.movies.presentation.movieList.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter

class MoviePagesAdapter(
    private val pageTitles: List<String>,
    private val requestAdapter: (position: Int) -> RecyclerView.Adapter<*>
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return RecyclerView(container.context)
            .apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = requestAdapter.invoke(position)
                if (itemDecorationCount == 0)
                    addItemDecoration(MovieListItemDecoration(context))
                container.addView(this)
            }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int = pageTitles.size

    override fun getPageTitle(position: Int): CharSequence {
        return pageTitles[position]
    }

}