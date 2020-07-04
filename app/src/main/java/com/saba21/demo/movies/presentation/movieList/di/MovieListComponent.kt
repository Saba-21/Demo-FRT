package com.saba21.demo.movies.presentation.movieList.di

import androidx.fragment.app.Fragment
import com.saba21.demo.movies.base.di.BaseFragmentComponent
import com.saba21.demo.movies.base.di.scopes.FragmentScope
import com.saba21.demo.movies.presentation.movieList.MovieListFragment
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [MovieListModule::class])
interface MovieListComponent : BaseFragmentComponent<MovieListFragment> {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: Fragment): MovieListComponent
    }

    override fun inject(fragment: MovieListFragment)

}