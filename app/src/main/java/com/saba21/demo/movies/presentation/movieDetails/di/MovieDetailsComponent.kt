package com.saba21.demo.movies.presentation.movieDetails.di

import androidx.fragment.app.Fragment
import com.saba21.demo.movies.base.di.BaseFragmentComponent
import com.saba21.demo.movies.base.di.scopes.FragmentScope
import com.saba21.demo.movies.presentation.movieDetails.MovieDetailsFragment
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [MovieDetailsModule::class])
interface MovieDetailsComponent : BaseFragmentComponent<MovieDetailsFragment> {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: Fragment): MovieDetailsComponent
    }

    override fun inject(fragment: MovieDetailsFragment)

}